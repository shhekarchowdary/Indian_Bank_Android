package com.arr.indianbankandroid;

public class SavingsProAccount extends Account {

    public SavingsProAccount(String accountNo, double currentBalance, String cin, String customerName) {
        super(accountNo, currentBalance, 2000, "Savings Pro Account", cin, customerName);
    }
}
