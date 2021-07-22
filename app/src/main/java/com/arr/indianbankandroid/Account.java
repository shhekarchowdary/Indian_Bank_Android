package com.arr.indianbankandroid;

import java.util.ArrayList;

public class Account {

    private String accountNo;
    private double currentBalance;
    private double minBalance;
    private String type;
    private String cin;
    private ArrayList<TransactionsHistory> transferHis = new ArrayList<>();

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private String customerName;

    public Account(String accountNo, double currentBalance, double minBalance, String type, String cin, String customerName) {
        this.accountNo = accountNo;
        this.currentBalance = currentBalance;
        this.minBalance = minBalance;
        this.type = type;
        this.cin = cin;
        this.customerName = customerName;
    }

    public ArrayList<TransactionsHistory> getTransferHis() {
        return transferHis;
    }

    public void setTransferHis(ArrayList<TransactionsHistory> transferHis) {
        this.transferHis = transferHis;
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
