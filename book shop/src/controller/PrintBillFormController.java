package controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PrintBillFormController implements Initializable {
    public TableView tblReceipt;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;

    public Label lblDate;
    public Label lblTime;
    public Label lblAmount;

    private void showDate() {
        lblDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTime();
        showDate();

    }
}

