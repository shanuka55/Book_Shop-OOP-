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

public class DashBordTwoFormController implements UiLoader {
    public AnchorPane context;
    public Button homeBtnId;
    public Button backBtnId;
    public Button orderBtnId;
    public Button customerBtnId;
    public Button companyOrderBtnId;
    public Button stockBtnId;
    public AnchorPane smallContext;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){
        showTime();
        showDate();
    }

    private void showDate() {
        lblDate.setText(new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new Date()));
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

    public void homeBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)homeBtnId.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/loginForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.show();
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void orderBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("orderForm");
    }

    public void customerBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("CustomerForm");
    }

    public void companyOrderBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("companyOrderForm");
    }

    public void stockBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadui("stockForm");
    }

    @Override
    public void loadui(String uiName) throws IOException {
        smallContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/"+uiName+".fxml"));
        smallContext.getChildren().add(parent);
    }
}
