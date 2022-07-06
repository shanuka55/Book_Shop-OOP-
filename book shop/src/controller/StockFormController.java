package controller;

import Util.CrudUtil;
import Util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.ItemsCrudController;
import controller.crudController.StockCrudController;
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
import model.TM.CustomerTM;
import model.TM.StockTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class StockFormController implements Initializable {
    public AnchorPane samallContext;

    public JFXComboBox cmbAddItemName;
    public JFXTextField txtAddItemCode;
    public JFXTextField txtAddItemQty;

    public TableView tblStock;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colPrice;

    public JFXButton btnAddItem;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();


    public void AddItemTostoresOnAction(ActionEvent actionEvent) {

    }

    private void setCustomerDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM stock");
        ObservableList<StockTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new StockTM(
                            result.getString("ItemCode"),
                            result.getString("ItemName"),
                            result.getInt("ItemQTY"),
                            result.getDouble("ItemPrice")
                    )
            );
        }
        tblStock.setItems(obList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addItemToStoreItem();

        try {
            setCustomerDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        Pattern qtyPattern = Pattern.compile("^^[0-9]{2,}$$");
        map.put(txtAddItemQty,qtyPattern);


    }
    private void addItemToStoreItem() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    StockCrudController.getAllItems()
            );
            cmbAddItemName.setItems(ObList);


            cmbAddItemName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemName = (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE ItemName=?",itemName);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtAddItemCode.setText(result.getString(1));

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
