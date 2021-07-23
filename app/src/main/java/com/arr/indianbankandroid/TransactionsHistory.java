package com.arr.indianbankandroid;


public class TransactionsHistory {

    private String type;
    private double Amount;
    private String description;
    private String transferDate;
    private String accountNo;

    public TransactionsHistory(String accountNo, String transferDate, String type, String description, double Amount){
        this.accountNo = accountNo;
        this.transferDate = transferDate;
        this.type = type;
        this.description = description;
        this.Amount = Amount;
    }

    public TransactionsHistory() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }



}
