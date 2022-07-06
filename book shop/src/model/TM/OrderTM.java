package model.TM;

import javafx.scene.control.Button;

public class OrderTM {
    private String ItemCode;
    private String Description;
    private double UnitPrice;
    private int Qty;
    private double Total;
    private Button btnDelete;

    public OrderTM() {
    }

    public OrderTM(String itemCode, String description, double unitPrice, int qty, double total, Button btnDelete) {
        ItemCode = itemCode;
        Description = description;
        UnitPrice = unitPrice;
        Qty = qty;
        Total = total;
        this.btnDelete = btnDelete;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", Qty=" + Qty +
                ", Total=" + Total +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
