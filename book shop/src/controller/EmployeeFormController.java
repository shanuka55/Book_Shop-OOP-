package controller;

import Util.CrudUtil;
import Util.ValidationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.EmployeeCrudController;
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
import model.Employee;
import model.Item;
import model.TM.CustomerTM;
import model.TM.EmployeeTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeeFormController implements Initializable {
    public AnchorPane smallContext;

    public JFXTextField txtAddEmployeeId;
    public JFXTextField txtAddEmployeeName;
    public JFXTextField txtAddEmployeeAddress;
    public JFXTextField txtAddEmployeeContact;
    public JFXTextField txtAddEmployeeSalary;

    public JFXComboBox cmbUpdateEmployeeId;
    public JFXTextField txtUpdateEmployeeName;
    public JFXTextField txtUpdateEmployeeAddress;
    public JFXTextField txtUpdateEmployeeContact;
    public JFXTextField txtUpdateEmployeeSalary;

    public JFXComboBox cmbDeleteEmployeeId;
    public JFXTextField txtDeleteEmployeeName;
    public JFXTextField txtDeleteEmployeeAddress;
    public JFXTextField txtDeleteEmployeeContact;
    public JFXTextField txtDeleteEmployeeSalary;

    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEmployeeId;
    public TableColumn colEmployeeName;
    public TableColumn colEmployeeAddress;
    public TableColumn colEmployeeContact;
    public TableColumn colEmployeeSalary;

    public JFXButton btnAddEmployee;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();


    String employeeId;



    public void doneAddEmployeeOnAction(ActionEvent actionEvent) {
        Employee e = new Employee(txtAddEmployeeId.getText(), txtAddEmployeeName.getText(), txtAddEmployeeAddress.getText(), txtAddEmployeeContact.getText(),Double.parseDouble(txtAddEmployeeSalary.getText()));

        try {
            if (CrudUtil.execute("INSERT INTO Employee VALUES (?,?,?,?,?)",e.getEmployeeId(),e.getEmployeeName(),e.getEmployeeAddress(),e.getEmployeeMobileNo(),e.getSalery() )){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException E) {
            E.printStackTrace();
            new Alert(Alert.AlertType.ERROR, E.getMessage()).show();
        }
    }

    public void doneUpdateItemOnAction(ActionEvent actionEvent) {
        Employee employee = new Employee(employeeId,txtUpdateEmployeeName.getText(),txtUpdateEmployeeAddress.getText(),txtAddEmployeeContact.getText(),Double.parseDouble(txtUpdateEmployeeSalary.getText()));
        try{
            if(CrudUtil.execute("UPDATE employee SET EmployeeName=?, EmployeeAddress=?, EmployeeMobileNo=?  WHERE Salery=?",employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeMobileNo(),employee.getSalery())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void deleteEmployee() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    EmployeeCrudController.getAllEmployeeId()
            );
            cmbDeleteEmployeeId.setItems(ObList);


            cmbDeleteEmployeeId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                employeeId= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM employee WHERE EmployeeId=?",employeeId);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtDeleteEmployeeName.setText(result.getString(2));
                        txtDeleteEmployeeAddress.setText(result.getString(3));
                        txtDeleteEmployeeContact.setText(result.getString(4));
                        txtDeleteEmployeeSalary.setText(result.getString(5));

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

    private void UpdateEmployee() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    EmployeeCrudController.getAllEmployeeId()
            );
            cmbUpdateEmployeeId.setItems(ObList);


            cmbUpdateEmployeeId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                employeeId= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM employee WHERE EmployeeId=?",employeeId);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtUpdateEmployeeName.setText(result.getString(2));
                        txtUpdateEmployeeAddress.setText(result.getString(3));
                        txtUpdateEmployeeContact.setText(result.getString(4));
                        txtUpdateEmployeeSalary.setText(result.getString(5));

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


    public void DeleteEmployeeOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM employee WHERE EmployeeId=?",employeeId)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Can't Delete!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateEmployee();
        deleteEmployee();

        try {
            setEmployeeDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("EmployeeAddress"));
        colEmployeeContact.setCellValueFactory(new PropertyValueFactory<>("EmployeeMobileNo"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        //add pattern and text to the map
        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(E0)[0-9]{2,}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contactPattern = Pattern.compile("^(077|078|072|070|076)[0-9]{7}$");
        Pattern salaryPattern = Pattern.compile("^^[0-9]{2,}.[0-9]{2}$");


        map.put(txtAddEmployeeId,idPattern);
        map.put(txtAddEmployeeName,namePattern);
        map.put(txtAddEmployeeAddress,addressPattern);
        map.put(txtAddEmployeeContact,contactPattern);
        map.put(txtAddEmployeeSalary,salaryPattern);

    }

    private void setEmployeeDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM employee");
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new EmployeeTM(
                            result.getString("EmployeeId"),
                            result.getString("EmployeeName"),
                            result.getString("EmployeeAddress"),
                            result.getString("EmployeeMobileNo"),
                            result.getDouble("salery")
                    )
            );
        }
        tblEmployee.setItems(obList);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddEmployee);



        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddEmployee);;

            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");

            }
        }
    }



}
