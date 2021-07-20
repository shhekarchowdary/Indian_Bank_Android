package com.arr.indianbankandroid;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Customer {

    private String cin;
    private String fullName;
    private String fatherName;
    private String dob;
    private String occupation;
    private String phoneNumber;
    private String emailId;
    private String address;
    private String city;
    private String panNumber;
    private String aadharNumber;
    private String accessCardNumber;
    private String pinNumber;

    FirebaseDatabase rootNode;
    DatabaseReference referenceCustomers;
    DatabaseReference referenceAccounts;

    private ArrayList<Account> accounts = new ArrayList<>();

    public Customer(String cin, String fullName, String fatherName, String dob, String occupation, String phoneNumber, String emailId, String address, String city, String panNumber, String aadharNumber, String accessCardNumber, String pinNumber) {
        this.cin = cin;
        this.fullName = fullName;
        this.fatherName = fatherName;
        this.dob = dob;
        this.occupation = occupation;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.address = address;
        this.city = city;
        this.panNumber = panNumber;
        this.aadharNumber = aadharNumber;
        this.accessCardNumber = accessCardNumber;
        this.pinNumber = pinNumber;

        this.rootNode = FirebaseDatabase.getInstance();
        this.referenceCustomers = rootNode.getReference("Customers");
        this.referenceAccounts = rootNode.getReference("Accounts");
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public int getNumberOfAccounts() {
        return this.accounts.size();
    }

    //method to get account

    public boolean createAccount(int type,String accountNumber,double initialAmount, String companyName, String empId) {
        switch (type)
        {
            case 1:
                if (initialAmount >= 0){
                    SavingsAccount acc = new SavingsAccount(accountNumber, initialAmount);
                    this.referenceAccounts.child(this.getCin()).child(acc.getAccountNo()).setValue(acc);
                    this.accounts.add(acc);
                    return true;
                }
                else{
                    return false;
                }
            case 2:
                if (initialAmount >= 2000){
                    SavingsProAccount acc = new SavingsProAccount(accountNumber, initialAmount);
                    this.referenceAccounts.child(this.getCin()).child(acc.getAccountNo()).setValue(acc);
                    this.accounts.add(acc);
                    return true;
                }
                else{
                    return false;
                }
            case 3:
                if (initialAmount >= 0){
                    SalaryAccount acc = new SalaryAccount(accountNumber, initialAmount, empId, companyName);
                    this.referenceAccounts.child(this.getCin()).child(acc.getAccountNo()).setValue(acc);
                    this.accounts.add(acc);
                    return true;
                }
                else{
                    return false;
                }
            default:
                return false;
        }
    }


    public Account getAccount(int type) {
        switch (type){
            case 1:
                for(Account account:this.accounts){
                    if(account.getType().equalsIgnoreCase("Savings Account")){
                        return account;
                    }
                }
                return null;
            case 2:
                for(Account account:this.accounts){
                    if(account.getType().equalsIgnoreCase("Savings Pro Account")){
                        return account;
                    }
                }
                return null;
            case 3:
                for(Account account:this.accounts){
                    if(account.getType().equalsIgnoreCase("Salary Account")){
                        return account;
                    }
                }
                return null;
            default:
                return null;
        }
    }

    public double checkBalance(int type) {
        Account account = getAccount(type);
        return account.getCurrentBalance();
    }

    public boolean transferMoney(int from, int to, double amount) {
        boolean check;
        Account wAccount = getAccount(from);
        Account dAccount = getAccount(to);

        if(wAccount != null && dAccount != null){
            if (amount <= wAccount.getCurrentBalance()){
                wAccount.setCurrentBalance(wAccount.getCurrentBalance() - amount);
                check = true;
            }
            else{
                check = false;
            }
            if(check){
                dAccount.setCurrentBalance(dAccount.getCurrentBalance() + amount);
                check = true;
            }
        }else{
            check = false;
        }
        return check;
    }

    public int payBills(int from, double amt) {
        Random r = new Random();
        int low;
        int high;
        int result;
        Account account = getAccount(from);
        if(amt < account.getCurrentBalance()){
            account.setCurrentBalance(account.getCurrentBalance() - amt);
        }else{
            return 0;
        }
        int low1 = 11111;
        int high1 = 99999;
        return r.nextInt(high1 - low1) + low1;
    }

    public double bookings(int bookingSelect, int number) {

        double payment;
        Random r = new Random();
        switch(bookingSelect) {
            case 1:
                payment = 250*number;
                break;
            case 2:
                int low1 = 1000;
                int high1 = 5000;
                int result = r.nextInt(high1-low1) + low1;
                payment = (double) result;
                break;
            case 3:
                int low2 = 250;
                int high2 = 700;
                int result2 = r.nextInt(high2-low2) + low2;
                payment = (double) result2;
                break;
            case 4:
                int low4 = 3500;
                int high4 = 4500;
                int result4 = r.nextInt(high4-low4) + low4;
                payment = (double) result4;
                break;
            default:
                return 0;
        }
        return payment;
    }


    public String getAccessCardNumber() {
        return accessCardNumber;
    }

    public void setAccessCardNumber(String accessCardNumber) {
        this.accessCardNumber = accessCardNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }
}
