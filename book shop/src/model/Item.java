package model;

public class Item {
    private String ICode;
    private String IName;
    private double IPrice;
    private int IQty;

    public Item() {
    }

    public Item(String ICode, String IName, double IPrice, int IQty) {
        this.ICode = ICode;
        this.IName = IName;
        this.IPrice = IPrice;
        this.IQty = IQty;
    }

    public String getICode() {
        return ICode;
    }

    public void setICode(String ICode) {
        this.ICode = ICode;
    }

    public String getIName() {
        return IName;
    }

    public void setIName(String IName) {
        this.IName = IName;
    }

    public double getIPrice() {
        return IPrice;
    }

    public void setIPrice(double IPrice) {
        this.IPrice = IPrice;
    }

    public int getIQty() {
        return IQty;
    }

    public void setIQty(int IQty) {
        this.IQty = IQty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ICode='" + ICode + '\'' +
                ", IName='" + IName + '\'' +
                ", IPrice=" + IPrice +
                ", IQty=" + IQty +
                '}';
    }
}
