package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import extra.UiLoader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBordFormController implements UiLoader {
    public AnchorPane context;
    public AnchorPane smallContext;
    public Button homeBtnId;
    public Button backBtnId;
    public Label lblDate;
    public Label lblTime;
    
    public void initialize(){
        showDate();
        showTime();
    }

    private void showTime() {
        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm aa ");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String times = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(times);
                });
            }
        });
        thread.start();
    }

    private void showDate() {
        lblDate.setText(new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new Date()));
    }

    public void homeBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)homeBtnId.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/loginForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.show();
    }

    public void backBtnOnAction(ActionEvent actionEvent) {
    }

    public void orderBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("orderForm");
    }

    public void customerBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("CustomerForm");
    }

    public void itemBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("itemForm");
    }

    public void companyOrderBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("companyOrderForm");
    }

    public void employeeBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("employeeForm");
    }

    public void stockBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("stockForm");
    }

    public void reportBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("reportsForm");
    }

    @Override
    public void loadui(String uiName) throws IOException {
        smallContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/"+uiName+".fxml"));
        smallContext.getChildren().add(parent);

    }
}
