    package controller;

import Util.CrudUtil;
import Util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.CompanyOrderCrudController;
import controller.crudController.ItemsCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.CompanyOrder;
import model.Customer;
import model.TM.CompanyOrderTM;
import model.TM.CustomerTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

    public class CompanyOrderFormController implements Initializable {
    public AnchorPane smallContext;

    public JFXTextField txtCompanyOrderId;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemQty;

    public JFXComboBox cmbCompanyId;
    public JFXTextField txtCompanyname;

    public TableView<CompanyOrderTM> tblCompanyOrder;
    public TableColumn colOrId;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colCompanyId;
    public TableColumn colCompanyName;
    public TableColumn colDelete;

    public JFXComboBox cmbDeleteOrderID;
    public JFXTextField txtDeleteCompanyname;

    public JFXButton btnAddOrder;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();


    private String CompanyId;

    public void doneCompanyOrderOnAction(ActionEvent actionEvent) {
        CompanyOrder c = new CompanyOrder(txtCompanyOrderId.getText(), (String) cmbItemCode.getValue(), txtItemName.getText(), Integer.parseInt(txtItemQty.getText()), (String) cmbCompanyId.getValue(), txtCompanyname.getText());

        try {
            if (CrudUtil.execute("INSERT INTO CompanyOrder VALUES (?,?,?,?,?,?)", c.getCompanyOrderId(), c.getItemCode(), c.getItemname(), c.getQty(), c.getCompanyId(), c.getCompanyName())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CompanyOrder();
        Company();
        SetDeleteOrder();

        try {
            setCompanyOrders();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colOrId.setCellValueFactory(new PropertyValueFactory<>("CompanyOrderId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Itemname"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colCompanyId.setCellValueFactory(new PropertyValueFactory<>("CompanyId"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));

        Pattern codePattern = Pattern.compile("^(or0)[0-9]{2,}$");
        Pattern qtyPattern = Pattern.compile("^^[0-9]{2,}$$");

        map.put(txtCompanyOrderId,codePattern);
        map.put(txtItemQty,qtyPattern);
    }

        private void SetDeleteOrder() {
            try {

                ObservableList<String> ObList = FXCollections.observableArrayList(
                        CompanyOrderCrudController.getOrderId()
                );
                cmbDeleteOrderID.setItems(ObList);


                cmbDeleteOrderID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    String orId = (String) newValue;

                    ResultSet result = null;
                    try {
                        result = CrudUtil.execute("SELECT * FROM companyorder WHERE CompanyOrderId=?", orId);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        if (result.next()) {
                            txtDeleteCompanyname.setText(result.getString(6));
                            //txtItemQty.setText(result.getString(3));

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

        private void CompanyOrder() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemsCrudController.getAllItems()
            );
            cmbItemCode.setItems(ObList);


            cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode = (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE ItemCode=?", itemCode);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtItemName.setText(result.getString(2));
                        //txtItemQty.setText(result.getString(3));

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

        private void Company() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CompanyOrderCrudController.getAllItems()
            );
            cmbCompanyId.setItems(ObList);


            cmbCompanyId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                CompanyId = (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM Company WHERE CompanyId=?", CompanyId);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtCompanyname.setText(result.getString(2));
                        //txtItemQty.setText(result.getString(3));

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

    ObservableList<CompanyOrderTM> tmList = FXCollections.observableArrayList();

    private void setCompanyOrders() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM companyOrder");
        ObservableList<CompanyOrderTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new CompanyOrderTM(
                            result.getString("CompanyOrderId"),
                            result.getString("ItemCode"),
                            result.getString("Itemname"),
                            result.getInt("qty"),
                            result.getString("CompanyId"),
                            result.getString("CompanyName")
                    )
            );
        }
        tblCompanyOrder.setItems(obList);
    }

    public void CompanyOrderDeleteOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM companyorder WHERE CompanyOrderId=?",(String)cmbDeleteOrderID.getValue())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Can't Delete!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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

