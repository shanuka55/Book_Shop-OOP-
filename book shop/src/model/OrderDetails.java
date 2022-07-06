package model;

public class OrderDetails {
    private String CustomerId;
    private String ItemCode;
    private String OrderId;
    private String ItemName;
    private int Qty;
    private double total;

    public OrderDetails() {
    }

    public OrderDetails(String customerId, String itemCode, String orderId, String itemName, int qty, double total) {
        CustomerId = customerId;
        ItemCode = itemCode;
        OrderId = orderId;
        ItemName = itemName;
        Qty = qty;
        this.total = total;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
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

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "CustomerId='" + CustomerId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", OrderId='" + OrderId + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Qty=" + Qty +
                ", total=" + total +
                '}';
    }
}
