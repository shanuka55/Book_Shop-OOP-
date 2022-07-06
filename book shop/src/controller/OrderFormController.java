package controller;

import Util.CrudUtil;
import Util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.CustomerCrudController;
import controller.crudController.ItemsCrudController;
import controller.crudController.OrderCrudController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Order;
import model.OrderDetail;
import model.OrderDetails;
import model.TM.OrderTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class OrderFormController implements Initializable {
    public AnchorPane smallContext;

    public JFXComboBox cmbItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemQtyOnHand;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtPlaceItemQty;

    public JFXComboBox cmbCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContact;


    public TableView tblOrder;
    public TableColumn colOrderItemCode;
    public TableColumn colOrderDescription;
    public TableColumn colOrderUnitPrice;
    public TableColumn colOrderQty;
    public TableColumn colOrderTotal;
    public TableColumn colOrderDelete;

    public Label lblTotal;

    public JFXButton btnAddOrder;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    String OrderId="Or00";
    int i=0;




    public void addToCartOnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(txtItemUnitPrice.getText());
        int qty = Integer.parseInt(txtPlaceItemQty.getText());
        double totalCost = unitPrice * qty;

        OrderTM isExists = isExists((String) cmbItemCode.getValue());

        if (isExists != null) {
            for (OrderTM temp : tmList
            ) {
                if (temp.equals(isExists)) {
                    temp.setQty((temp.getQty() + qty));
                    temp.setTotal(temp.getTotal() + totalCost);
                }
            }
        } else {
            Button btn = new Button("Delete");

            OrderTM tm = new OrderTM(
                    (String) cmbItemCode.getValue(),
                    txtItemName.getText(),
                    unitPrice,
                    qty,
                    totalCost,
                    btn
            );

            btn.setOnAction(e -> {
                tmList.remove(tm);
                calculateTotal();
            });

            tmList.add(tm);
            tblOrder.setItems(tmList);
        }
        tblOrder.refresh();
        calculateTotal();

    }
    ObservableList<OrderTM> tmList = FXCollections.observableArrayList();

    private OrderTM isExists(String itemCode) {
        for (OrderTM tm : tmList
        ) {
            if (tm.getItemCode().equals(itemCode)) {
                return tm;
            }
        }
        return null;
    }

    private void calculateTotal() {
        double total = 0;
        for (OrderTM tm : tmList
        ) {
            total += tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException {
        i=i+1;
        String code= OrderId + i;
        Order order = new Order(
                code,
                txtItemName.getText(),
                Double.parseDouble(txtItemUnitPrice.getText()),
                Integer.parseInt(txtPlaceItemQty.getText()),
                Double.parseDouble(lblTotal.getText())
        );
        ArrayList<OrderDetails> details = new ArrayList<>();
        for (OrderTM tm : tmList
        ) {
            details.add(
                    new OrderDetails(
                            (String) cmbCustomerId.getValue(),
                            tm.getItemCode(),
                            code,
                            tm.getDescription(),
                            tm.getQty(),
                            tm.getTotal()
                    )
            );
        }

        //----------------------------

        Connection connection= null;

        try {
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isOrderSaved = new OrderCrudController().saveOrder(order);
            if (isOrderSaved) {
                boolean isDetailsSaved=new OrderCrudController().saveOrderDetails(details);
                if (isDetailsSaved){
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
                }else{
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR,"Error!").show();
                }
            }else{
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"Error!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }finally {
            connection.setAutoCommit(true);
        }

        //pass value for orderDeatail Database Table
        String date=new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        OrderDetail orderDetail = new OrderDetail((String) cmbCustomerId.getValue(),code,date,Double.parseDouble(lblTotal.getText()));
        try {
            CrudUtil.execute("INSERT INTO orderDeatail VALUES (?,?,?,?)",orderDetail.getCustomerId(),orderDetail.getOrderId(),orderDetail.getDate(),orderDetail.getTotal());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new Alert (Alert.AlertType.CONFIRMATION,"Order Saved").show();


    }


    public void printBillOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/PrintBillForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerCode();
        addItem();

        colOrderItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colOrderDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colOrderUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colOrderTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        colOrderDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        Pattern qtyPattern = Pattern.compile("^1|[0-9]{2,}$");
        map.put(txtPlaceItemQty,qtyPattern);
    }
    private void setCustomerCode() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getAllCustomer()
            );
            cmbCustomerId.setItems(ObList);


            cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String customerId = (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM customer WHERE CustomerId=?",customerId);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtCustomerName.setText(result.getString(2));
                        txtCustomerAddress.setText(result.getString(3));
                        txtCustomerContact.setText(String.valueOf(result.getInt(4)));

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty Result").show();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void addItem() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemsCrudController.getAllItems()
            );
            cmbItemCode.setItems(ObList);


            cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode = (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE ItemCode=?",itemCode);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtItemName.setText(result.getString(2));
                        txtItemQtyOnHand.setText(String.valueOf(result.getInt(4)));
                        txtItemUnitPrice.setText(String.valueOf(result.getDouble(3)));


                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty Result").show();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddOrder);



        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddOrder);;

            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");

            }
        }
    }
}
