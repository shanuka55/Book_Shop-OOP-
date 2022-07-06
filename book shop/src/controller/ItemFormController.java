package controller;

import Util.CrudUtil;
import Util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.ItemsCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Item;
import model.TM.ItemTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ItemFormController implements Initializable {
    public AnchorPane samllContext;

    public JFXTextField txtAddItemCode;
    public JFXTextField txtAddItemName;
    public JFXTextField txtAddItemPrice;
    public JFXTextField txtAddItemQty;

    public JFXComboBox cmdUpdateItemCode;
    public JFXTextField txtUpdateItemName;
    public JFXTextField txtUpdateItemPrice;
    public JFXTextField txtUpdateItemQty;

    public JFXTextField txtDeleteItemName;
    public JFXTextField txtDeleteItemQty;
    public JFXComboBox cmdDeleteItemCode;
    public JFXTextField txtDeleteItemPrice;

    public TableView<ItemTM> tblItem;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemPrice;
    public TableColumn colItemQty;

    public JFXButton btnAddItem;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    String itemCode;



    public void btnAddItemOnAction(ActionEvent actionEvent) {
        Item i = new Item(txtAddItemCode.getText(), txtAddItemName.getText(),Double.parseDouble(txtAddItemPrice.getText()), Integer.parseInt(txtAddItemQty.getText()));

        try {
            if (CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)",i.getICode(),i.getIName(),i.getIPrice(),i.getIQty())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }



    public void DeleteItemOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM item WHERE ItemCode=?",itemCode)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Can't Delete!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void DeleteItem() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemsCrudController.getAllItems()
            );
            cmdDeleteItemCode.setItems(ObList);


            cmdDeleteItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE ItemCode=?",itemCode);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtDeleteItemName.setText(result.getString(2));
                        txtDeleteItemPrice.setText(String.valueOf(result.getDouble(3)));
                        txtDeleteItemQty.setText(result.getString(4));

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

    private void UpdateItem() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemsCrudController.getAllItems()
            );
            cmdUpdateItemCode.setItems(ObList);


            cmdUpdateItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE ItemCode=?",itemCode);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtUpdateItemName.setText(result.getString(2));
                        txtUpdateItemPrice.setText(String.valueOf(result.getDouble(3)));
                        txtUpdateItemQty.setText(result.getString(4));

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



    public void btnUpdateItemOnAction(ActionEvent actionEvent) throws SQLException {
        Item item = new Item(itemCode,txtUpdateItemName.getText(),Double.parseDouble(txtUpdateItemPrice.getText()),Integer.parseInt(txtUpdateItemQty.getText()));
        try{
            if(CrudUtil.execute("UPDATE item SET ItemName=?, ItemPrice=?, ItemQTY=?  WHERE ItemCode=?",item.getIName(),item.getIPrice(),item.getIQty(),item.getICode())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateItem();
        DeleteItem();
        try {
            setItemDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("ItemQty"));

        //validaion
        Pattern codePattern = Pattern.compile("^(I0)[0-9]{2,}$");
        Pattern namePattern = Pattern.compile("^[A-z ()0-9]{3,}$");
        Pattern pricePattern = Pattern.compile("^[0-9]{2,}.[0-9]{2}$");
        Pattern qtyPattern = Pattern.compile("^^[0-9]{2,}$$");

        map.put(txtAddItemCode,codePattern);
        map.put(txtAddItemName,namePattern);
        map.put(txtAddItemPrice,pricePattern);
        map.put(txtAddItemQty,qtyPattern);

    }



    private void setItemDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM item");
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new ItemTM(
                            result.getString("ItemCode"),
                            result.getString("ItemName"),
                            result.getDouble("ItemPrice"),
                            result.getInt("ItemQty")
                    )
            );
        }
        tblItem.setItems(obList);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddItem);



        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddItem);;

            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");

            }
        }
    }


}
