package model;

public class Customer {
    private String CId;
    private String CName;
    private String CAddress;
    private String CContact;

    public Customer() {
    }

    public Customer(String CId, String CName, String CAddress, String CContact) {
        this.CId = CId;
        this.CName = CName;
        this.CAddress = CAddress;
        this.CContact = CContact;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCAddress() {
        return CAddress;
    }

    public void setCAddress(String CAddress) {
        this.CAddress = CAddress;
    }

    public String getCContact() {
        return CContact;
    }

    public void setCContact(String CContact) {
        this.CContact = CContact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CId='" + CId + '\'' +
                ", CName='" + CName + '\'' +
                ", CAddress='" + CAddress + '\'' +
                ", CContact='" + CContact + '\'' +
                '}';
    }
}
