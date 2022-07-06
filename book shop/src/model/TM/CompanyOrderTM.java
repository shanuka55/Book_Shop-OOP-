package model.TM;

import javafx.scene.control.Button;

public class CompanyOrderTM {
    private String CompanyOrderId;
    private String ItemCode;
    private String Itemname;
    private int Qty;
    private String CompanyId;
    private String CompanyName;

    public CompanyOrderTM() {
    }

    public CompanyOrderTM(String companyOrderId, String itemCode, String itemname, int qty, String companyId, String companyName) {
        CompanyOrderId = companyOrderId;
        ItemCode = itemCode;
        Itemname = itemname;
        Qty = qty;
        CompanyId = companyId;
        CompanyName = companyName;
    }

    public String getCompanyOrderId() {
        return CompanyOrderId;
    }

    public void setCompanyOrderId(String companyOrderId) {
        CompanyOrderId = companyOrderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    @Override
    public String toString() {
        return "CompanyOrderTM{" +
                "CompanyOrderId='" + CompanyOrderId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", Itemname='" + Itemname + '\'' +
                ", Qty=" + Qty +
                ", CompanyId='" + CompanyId + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                '}';
    }
}
