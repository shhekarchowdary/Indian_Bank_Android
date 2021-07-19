package com.arr.indianbankandroid;

public class Account {

    private String accountNo;
    private double currentBalance;
    private double minBalance;
    private String type;

    public Account(String accountNo, double currentBalance, double minBalance, String type) {
        this.accountNo = accountNo;
        this.currentBalance = currentBalance;
        this.minBalance = minBalance;
        this.type = type;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public String getType() {
        return type;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
