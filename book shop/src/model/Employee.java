package model;

public class Employee {
    private String EmployeeId;
    private String EmployeeName;
    private String EmployeeAddress;
    private String EmployeeMobileNo;
    private double salery;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, String employeeAddress, String employeeMobileNo, double salery) {
        EmployeeId = employeeId;
        EmployeeName = employeeName;
        EmployeeAddress = employeeAddress;
        EmployeeMobileNo = employeeMobileNo;
        this.salery = salery;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return EmployeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        EmployeeAddress = employeeAddress;
    }

    public String getEmployeeMobileNo() {
        return EmployeeMobileNo;
    }

    public void setEmployeeMobileNo(String employeeMobileNo) {
        EmployeeMobileNo = employeeMobileNo;
    }

    public double getSalery() {
        return salery;
    }

    public void setSalery(double salery) {
        this.salery = salery;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId='" + EmployeeId + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", EmployeeAddress='" + EmployeeAddress + '\'' +
                ", EmployeeMobileNo='" + EmployeeMobileNo + '\'' +
                ", salery=" + salery +
                '}';
    }
}
