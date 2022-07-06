package model.TM;

public class EmployeeTM {
    private String EmployeeId;
    private String EmployeeName;
    private String EmployeeAddress;
    private String EmployeeMobileNo;
    private double salary;

    public EmployeeTM() {
    }

    public EmployeeTM(String employeeId, String employeeName, String employeeAddress, String employeeMobileNo, double salary) {
        EmployeeId = employeeId;
        EmployeeName = employeeName;
        EmployeeAddress = employeeAddress;
        EmployeeMobileNo = employeeMobileNo;
        this.salary = salary;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "EmployeeId='" + EmployeeId + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", EmployeeAddress='" + EmployeeAddress + '\'' +
                ", EmployeeMobileNo='" + EmployeeMobileNo + '\'' +
                ", salary=" + salary +
                '}';
    }
}
