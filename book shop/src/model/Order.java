package model;

public class Order {
    private String OrderId;
    private String ItemName;
    private double OrderUnitPrice;
    private int OrderQTY;
    private double Total;

    public Order() {
    }

    public Order(String orderId, String itemName, double orderUnitPrice, int orderQTY, double total) {
        OrderId = orderId;
        ItemName = itemName;
        OrderUnitPrice = orderUnitPrice;
        OrderQTY = orderQTY;
        Total = total;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public double getOrderUnitPrice() {
        return OrderUnitPrice;
    }

    public void setOrderUnitPrice(double orderUnitPrice) {
        OrderUnitPrice = orderUnitPrice;
    }

    public int getOrderQTY() {
        return OrderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        OrderQTY = orderQTY;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderId='" + OrderId + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", OrderUnitPrice=" + OrderUnitPrice +
                ", OrderQTY=" + OrderQTY +
                ", Total=" + Total +
                '}';
    }
}
