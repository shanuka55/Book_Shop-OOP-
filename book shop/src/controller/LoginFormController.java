package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane context;
    public JFXPasswordField txtPassword;
    public JFXTextField txtUsername;
    public JFXButton loginbtnId;
    public JFXButton cancelBtnId;

    String ownerName="owner";
    String ownerPsw="owner";
    String cashierName="cashier";
    String cashierPsw="1234";

    public void loadUi(String location) throws IOException {
        Stage stage = (Stage) loginbtnId.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.show();
    }

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
       if(txtUsername.getText().equals(ownerName) && txtPassword.getText().equals(ownerPsw)){
           loadUi("dashBordForm");
           new Alert(Alert.AlertType.CONFIRMATION, "Login Successful(OWNER)....!, Welcome to the Puwasa Book Shop").show();

       }else if(txtUsername.getText().equals(cashierName) && txtPassword.getText().equals(cashierPsw)){
           loadUi("dashBordTwoForm");
           new Alert(Alert.AlertType.CONFIRMATION, "Login Successful(CASHIER)....!, Welcome to the Puwasa Book Shop").show();
       }else{
           new Alert(Alert.AlertType.ERROR, "Login Failed....!, Please enter user name and password correctly").show();
        }
    }

    public void cancelBtnOnAction(ActionEvent actionEvent) {
        txtUsername.clear();
        txtPassword.clear();
    }
}
