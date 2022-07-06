package model;

public class OrderDetail {
    private String CustomerId;
    private String OrderId;
    private String Date;
    private double Total;

    public OrderDetail() {
    }

    public OrderDetail(String customerId, String orderId, String date, double total) {
        CustomerId = customerId;
        OrderId = orderId;
        Date = date;
        Total = total;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "CustomerId='" + CustomerId + '\'' +
                ", OrderId='" + OrderId + '\'' +
                ", Date='" + Date + '\'' +
                ", Total=" + Total +
                '}';
    }
}
