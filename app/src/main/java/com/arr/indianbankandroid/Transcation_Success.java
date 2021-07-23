package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Transcation_Success extends AppCompatActivity {
    TextView transSuccess,cusName,cusAvlBal;
    Customer cusdata;
    ImageView img;
    Account tempAccDetails,fromaccdata,payBillsAccount;
    int ts,t,t1;
    int transId,payAccount,fromPayBills,fromBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation__success);
        transSuccess=findViewById(R.id.tvTranscationSucessTxt);
        cusName=findViewById(R.id.tvCustomerName);
        cusAvlBal=findViewById(R.id.tvAvlBalanceTxt);

        cusdata=LoginActivity2.loggedInCustomer;
        img=findViewById(R.id.imgTick);
        tempAccDetails=TransferToOthersAccountActivity.tempacc;
        fromaccdata=BankTranscations.fromaccdata;
        ts=TransferToOthersAccountActivity.ts;
        t=BankingSubMenu.tm;
        t1=MainMenu.t1;
        img.setImageResource(R.mipmap.tick);
        transId = getIntent().getIntExtra("transId",1);
        payAccount = getIntent().getIntExtra("payAccount",0);
        fromPayBills = getIntent().getIntExtra("fromPayBills",0);
        fromBookings = getIntent().getIntExtra("fromBookings",0);
        Account pAccount = cusdata.getAccount(payAccount);

        if(fromPayBills == 1){
            transSuccess.setText("Bill Payment Successful");
            cusName.setText(new StringBuilder().append("Payment Id:").append(String.valueOf(transId)).toString());
            cusAvlBal.setText(new StringBuilder().append("Available Balance:").append(Double.toString(pAccount.getCurrentBalance())).toString());
        }else if(fromBookings == 1){
            transSuccess.setText("Booking Successful");
            cusName.setText(new StringBuilder().append("Booking Id:").append(String.valueOf(transId)).toString());
            cusAvlBal.setText(new StringBuilder().append("Available Balance:").append(Double.toString(pAccount.getCurrentBalance())).toString());
        }else{
            if(ts==1){
                transSuccess.setText("Transaction Sucessfull");
                cusName.setText("Customer Name:"+cusdata.getFullName().toString());
                cusAvlBal.setText("Available Balance: "+String.valueOf(tempAccDetails.getCurrentBalance()).toString());
            }
            else if(t==1 || t1==1){
                transSuccess.setText("Transaction Sucessfull");
                cusName.setText("Customer Name:"+cusdata.getFullName().toString());
                cusAvlBal.setText("Available Balance: "+String.valueOf(fromaccdata.getCurrentBalance()).toString());
            }
        }

    }

    @Override
    public void onBackPressed() {
        if(fromPayBills == 1){
            Intent inn = new Intent(getBaseContext(),PaybillsActivity.class);
            inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
            inn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(inn);
            finish(); // finish the current activity
        }else if(fromBookings == 1){
            Intent inn = new Intent(getBaseContext(),BookingsActivity.class);
            inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
            inn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(inn);
            finish(); // finish the current activity
        }else if(t1 == 1){
            Intent inn = new Intent(getBaseContext(),MainMenu.class);
            inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
            inn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(inn);
            finish(); // finish the current activity
        }else if(ts==1 || t==1){
            Intent inn = new Intent(getBaseContext(),BankingSubMenu.class);
            inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
            inn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(inn);
            finish(); // finish the current activity
        }

    }
}