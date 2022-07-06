package model.TM;

public class CustomerTM {
    private String customerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerMobileNo;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String customerName, String customerAddress, String customerMobileNo) {
        this.customerId = customerId;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
        CustomerMobileNo = customerMobileNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerMobileNo() {
        return CustomerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        CustomerMobileNo = customerMobileNo;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId='" + customerId + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                ", CustomerMobileNo='" + CustomerMobileNo + '\'' +
                '}';
    }
}