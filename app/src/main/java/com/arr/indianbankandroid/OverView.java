package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OverView extends AppCompatActivity {

    TextView mCin, mCustomerName, mFatherName, mDob, mMobile, mEmail, mAddress, mAadhar, mPan,
            mAccount1, mAccount2, mAccount3, mAccount1Number, mAccount2Number, mAccount3Number,
            mAccount1Balance, mAccount2Balance, mAccount3Balance,mCombinedBalance,mAccount1Bal,mAccount2Bal,mAccount3Bal;

    Customer loggedInCustomer = LoginActivity2.loggedInCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        mCin = findViewById(R.id.tvCin);
        mCustomerName = findViewById(R.id.tvCustomerName);
        mFatherName = findViewById(R.id.tvFatherName);
        mDob = findViewById(R.id.tvDateOfBirth);
        mMobile = findViewById(R.id.tvMobileNumber);
        mEmail = findViewById(R.id.tvEmailId);
        mAddress = findViewById(R.id.tvAddress);
        mAadhar = findViewById(R.id.tvAadharNumber);
        mPan = findViewById(R.id.tvPanNumber);
        mAccount1 = findViewById(R.id.tvmAccount1);
        mAccount2 = findViewById(R.id.tvmAccount2);
        mAccount3 = findViewById(R.id.tvmAccount3);
        mAccount1Number = findViewById(R.id.tvAccount1Number);
        mAccount2Number = findViewById(R.id.tvAccount2Number);
        mAccount3Number = findViewById(R.id.tvAccount3Number);
        mAccount1Balance = findViewById(R.id.tvAccount1Balance);
        mAccount2Balance = findViewById(R.id.tvAccount2Balance);
        mAccount3Balance = findViewById(R.id.tvAccount3Balance);
        mCombinedBalance = findViewById(R.id.tvCombinedBalance);
        mAccount1Bal= findViewById(R.id.tvmAccount1Balance);
        mAccount2Bal= findViewById(R.id.tvmAccount2Balance);
        mAccount3Bal= findViewById(R.id.tvmAccount3Balance);


        mCin.setText(loggedInCustomer.getCin());
        mCustomerName.setText(loggedInCustomer.getFullName());
        mFatherName.setText(loggedInCustomer.getFatherName());
        mDob.setText(loggedInCustomer.getDob());
        mMobile.setText(loggedInCustomer.getPhoneNumber());
        mEmail.setText(loggedInCustomer.getEmailId());
        mAddress.setText(loggedInCustomer.getAddress());
        mAadhar.setText(loggedInCustomer.getAadharNumber());
        mPan.setText(loggedInCustomer.getPanNumber());

        double combinedBalance = 0;
        for(Account account:loggedInCustomer.getAccounts()){
            combinedBalance += account.getCurrentBalance();
        }
        mCombinedBalance.setText(String.format("%.2f",combinedBalance));

        int noOfAccounts = loggedInCustomer.getAccounts().size();

        if(noOfAccounts == 3){
            mAccount1.setText(loggedInCustomer.getAccounts().get(0).getType());
            mAccount2.setText(loggedInCustomer.getAccounts().get(1).getType());
            mAccount3.setText(loggedInCustomer.getAccounts().get(2).getType());
            mAccount1Number.setText(loggedInCustomer.getAccounts().get(0).getAccountNo());
            mAccount2Number.setText(loggedInCustomer.getAccounts().get(1).getAccountNo());
            mAccount3Number.setText(loggedInCustomer.getAccounts().get(2).getAccountNo());
            mAccount1Balance.setText(String.format("%.2f",loggedInCustomer.getAccounts().get(0).getCurrentBalance()));
            mAccount2Balance.setText(String.format("%.2f",loggedInCustomer.getAccounts().get(1).getCurrentBalance()));
            mAccount3Balance.setText(String.format("%.2f",loggedInCustomer.getAccounts().get(2).getCurrentBalance()));
        }else if(noOfAccounts ==2){
            mAccount1.setText(loggedInCustomer.getAccounts().get(0).getType());
            mAccount2.setText(loggedInCustomer.getAccounts().get(1).getType());
            mAccount1Number.setText(loggedInCustomer.getAccounts().get(0).getAccountNo());
            mAccount2Number.setText(loggedInCustomer.getAccounts().get(1).getAccountNo());
            mAccount1Balance.setText(String.format("%.2f",loggedInCustomer.getAccounts().get(0).getCurrentBalance()));
            mAccount2Balance.setText(String.format("%.2f",loggedInCustomer.getAccounts().get(1).getCurrentBalance()));
            mAccount3.setVisibility(View.GONE);
            mAccount3Bal.setVisibility(View.GONE);
            mAccount3Number.setVisibility(View.GONE);
            mAccount3Balance.setVisibility(View.GONE);
        }
        else if(noOfAccounts ==1){
            mAccount1.setText(loggedInCustomer.getAccounts().get(0).getType());
            mAccount1Number.setText(loggedInCustomer.getAccounts().get(0).getAccountNo());
            mAccount1Balance.setText(String.format("%.2f",loggedInCustomer.getAccounts().get(0).getCurrentBalance()));
            mAccount2.setVisibility(View.GONE);
            mAccount2Bal.setVisibility(View.GONE);
            mAccount2Number.setVisibility(View.GONE);
            mAccount2Balance.setVisibility(View.GONE);
            mAccount3.setVisibility(View.GONE);
            mAccount3Bal.setVisibility(View.GONE);
            mAccount3Number.setVisibility(View.GONE);
            mAccount3Balance.setVisibility(View.GONE);
        }



    }


}