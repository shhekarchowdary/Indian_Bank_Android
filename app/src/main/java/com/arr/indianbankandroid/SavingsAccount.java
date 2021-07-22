package com.arr.indianbankandroid;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNo, double currentBalance, String cin, String customerName) {
        super(accountNo, currentBalance, 0.0, "Savings Account",cin,customerName);
    }
}
