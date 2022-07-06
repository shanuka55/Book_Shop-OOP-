package model.TM;

public class ItemTM {
    private String ItemCode;
    private String ItemName;
    private double ItemPrice;
    private int ItemQty;

    public ItemTM() {
    }

    public ItemTM(String itemCode, String itemName, double itemPrice, int itemQty) {
        ItemCode = itemCode;
        ItemName = itemName;
        ItemPrice = itemPrice;
        ItemQty = itemQty;
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

    public double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(double itemPrice) {
        ItemPrice = itemPrice;
    }

    public int getItemQty() {
        return ItemQty;
    }

    public void setItemQty(int itemQty) {
        ItemQty = itemQty;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", ItemPrice=" + ItemPrice +
                ", ItemQty=" + ItemQty +
                '}';
    }
}
