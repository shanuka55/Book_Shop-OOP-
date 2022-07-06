package model.TM;

import javafx.scene.control.Button;

public class StockTM {
    private String ItemCode;
    private String ItemName;
    private int qty;
    private double price;

    public StockTM() {
    }

    public StockTM(String itemCode, String itemName, int qty, double price) {
        ItemCode = itemCode;
        ItemName = itemName;
        this.qty = qty;
        this.price = price;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StockTM{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
