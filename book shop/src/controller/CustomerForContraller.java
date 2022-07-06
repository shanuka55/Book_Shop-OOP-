package controller;

import Util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.CustomerCrudController;
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
import model.Customer;
import Util.CrudUtil;
import model.Item;
import model.TM.CustomerTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerForContraller implements Initializable {
    public AnchorPane smallContext;
    public JFXTextField txtAddCustomerId;
    public JFXTextField txtAddCustomerContact;
    public JFXTextField txtAddCustomerAddress;
    public JFXTextField txtAddCustomerName;


    public JFXComboBox cmbUpdateCustomerId;
    public JFXTextField txtUpdateCustomerName;
    public JFXTextField txtUpdateCustomerAddress;
    public JFXTextField txtUpdateCustomerContact;


    public JFXComboBox cmbDeleteteCustomerId;
    public JFXTextField txtDeleteCustomerName;
    public JFXTextField txtDeleteCustomerContact;
    public JFXTextField txtDeleteCustomerAddress;

    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerContact;
    public JFXButton btnCustomerAdd;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    String customerId;


    public void customerAddOnAction(ActionEvent actionEvent) {
        //Add to Customer data where Database Table
        Customer c = new Customer(txtAddCustomerId.getText(), txtAddCustomerName.getText(), txtAddCustomerAddress.getText(), txtAddCustomerContact.getText());

        try {
            if (CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)",c.getCId(),c.getCName(),c.getCAddress(),c.getCContact())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void customerUpdateOnAction(ActionEvent ActionEvent) {
        Customer customer=new Customer(customerId,txtUpdateCustomerName.getText(), txtUpdateCustomerAddress.getText(), txtUpdateCustomerContact.getText());
        try{
            if(CrudUtil.execute("UPDATE customer SET CustomerName=?, CustomerAddress=?, CustomerMobileNo=?  WHERE CustomerId=?",customer.getCName(),customer.getCAddress(),customer.getCContact(),customer.getCId())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void doneDeleteOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM customer WHERE CustomerId=?",customerId)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Can't Delete!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void deleteCustomerId() {
        //Set Text Field For Delete Customer Deatails
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getAllCustomer()
            );
            cmbDeleteteCustomerId.setItems(ObList);


            cmbDeleteteCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                customerId= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM customer WHERE CustomerId=?",customerId);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtDeleteCustomerName.setText(result.getString(2));
                        txtDeleteCustomerAddress.setText(result.getString(3));
                        txtDeleteCustomerContact.setText(String.valueOf(result.getInt(4)));

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

    private void setCustomerCode() {
        //Set Text Field For Update Customer Deatails
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getAllCustomer()
            );
            cmbUpdateCustomerId.setItems(ObList);


            cmbUpdateCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                customerId= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM customer WHERE CustomerId=?",customerId);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtUpdateCustomerName.setText(result.getString(2));
                        txtUpdateCustomerAddress.setText(result.getString(3));
                        txtUpdateCustomerContact.setText(String.valueOf(result.getInt(4)));

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerCode(); //Show CmbDeatails
        deleteCustomerId(); //Show CmbDeatails
        try {
            setCustomerDetails();
        } catch (SQLException throwables) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2 Show Customer Deatails For InterFace Customer Table
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("CustomerMobileNo"));


        //add pattern and text to the map
        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(C0)[0-9]{2,}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contactPattern = Pattern.compile("^(077|078|072|070|076)[0-9]{7}$");

        map.put(txtAddCustomerId,idPattern);
        map.put(txtAddCustomerName,namePattern);
        map.put(txtAddCustomerAddress,addressPattern);
        map.put(txtAddCustomerContact,contactPattern);


    }


    private void setCustomerDetails() throws SQLException, ClassNotFoundException {
        //1 Sent Customer deatails For Customer interFace Table
        ResultSet result = CrudUtil.execute("SELECT * FROM customer");
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new CustomerTM(
                            result.getString("CustomerId"),
                            result.getString("CustomerName"),
                            result.getString("CustomerAddress"),
                            result.getString("CustomerMobileNo")
                    )
            );
        }
        tblCustomer.setItems(obList);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnCustomerAdd);



        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnCustomerAdd);;

            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");

            }
        }
    }

}
