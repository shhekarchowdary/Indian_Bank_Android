package com.arr.indianbankandroid;

public class SalaryAccount extends Account {
    private String empId;
    private String companyName;

    public SalaryAccount(String accountNo, double currentBalance, String empId, String companyName) {
        super(accountNo, currentBalance, 0, "Salary Account");
        this.empId = empId;
        this.companyName = companyName;
    }

    public String getEmpId() {
        return empId;
    }

    public String getCompanyName() {
        return companyName;
    }
}
