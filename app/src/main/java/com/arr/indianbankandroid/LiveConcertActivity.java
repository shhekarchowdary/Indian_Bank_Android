package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class LiveConcertActivity extends AppCompatActivity {

    EditText noftickets;
    Button concertbtn,getfareconcert;
    RadioGroup concertradiogrp;
    RadioButton musicconcert,miniconcert,familyconcert;
    Spinner concertspinner;
    TextView fareconcerttv;

    boolean radioClicked = false;
    Customer loggedInCustomer;
    ArrayList<String> mAccounts = new ArrayList<>();
    double payment;
    int transId,accountSel;

    FirebaseDatabase rootNode;
    DatabaseReference referenceCustomers;
    DatabaseReference referenceAccounts;
    DatabaseReference referenceTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_concert);


        noftickets = findViewById(R.id.liveconcerttickets);
        concertbtn = findViewById(R.id.liveconcertbtn);
        concertradiogrp = findViewById(R.id.concertradiogrp1);
        musicconcert = findViewById(R.id.musicradio);
        miniconcert = findViewById(R.id.miniconcertbtn);
        familyconcert = findViewById(R.id.familybtn);
        getfareconcert = findViewById(R.id.getfareconcertbtn);
        concertspinner = findViewById(R.id.concertspinner);
        fareconcerttv = findViewById(R.id.concertfaretv);

        rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        referenceAccounts = rootNode.getReference("Accounts");
        referenceTransactions = rootNode.getReference("Transactions");

        mAccounts.clear();
        loggedInCustomer = LoginActivity2.loggedInCustomer;
        for (Account account : loggedInCustomer.getAccounts()) {
            mAccounts.add(account.getType());
        }

        ArrayAdapter spinAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, mAccounts);
        concertspinner.setAdapter(spinAdapter);

        musicconcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        familyconcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        miniconcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        getfareconcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!noftickets.getText().toString().trim().isEmpty()){
                    String no = noftickets.getText().toString().trim();
                    int num = Integer.parseInt(no);
                    if(num < 5){
                        if(radioClicked){
                            payment = loggedInCustomer.bookings(3,num);
                            fareconcerttv.setText(Double.toString(payment));
                        }else
                             Snackbar.make(getfareconcert, "Select Concert", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getBaseContext(),"Select Concert",Toast.LENGTH_LONG).show();
                    }else
                         Snackbar.make(getfareconcert, "Max 5 tickets only per Id", Snackbar.LENGTH_LONG).show();
                       // Toast.makeText(getBaseContext(),"Max 5 tickets only per Id",Toast.LENGTH_LONG).show();
                }else
                    Snackbar.make(getfareconcert, "Enter Number Of Tickets", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(),"Enter Number Of Tickets",Toast.LENGTH_LONG).show();
            }
        });


        concertspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String accountSelected = mAccounts.get(i);
                if(accountSelected.equals("Savings Account")){
                    accountSel = 1;
                }else if(accountSelected.equals("Savings Pro Account")){
                    accountSel = 2;
                }else if(accountSelected.equals("Salary Account")){
                    accountSel = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                accountSel = 1;
            }
        });

        concertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                Account account = loggedInCustomer.getAccount(accountSel);
                if(payment < account.getCurrentBalance()){
                    double value = account.getCurrentBalance() - payment;
                    account.setCurrentBalance(value);
                    referenceAccounts.child(account.getAccountNo()).child("currentBalance").setValue(value);
                    String date = new SimpleDateFormat("yyyy-MM-DD").format(new Date());
                    TransactionsHistory transac = new TransactionsHistory(account.getAccountNo(),date,"Debit","For Live Concert",payment);
                    account.getTransferHis().add(transac);
                    String currentTime = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss z").format(new Date());
                    referenceTransactions.child(currentTime+transac.getAccountNo()).setValue(transac);
                    int low = 11111;
                    int high = 99999;
                    transId =  r.nextInt(high - low) + low;
                    Intent i = new Intent(getBaseContext(),Transcation_Success.class);
                    i.putExtra("transId",transId);
                    i.putExtra("payAccount",accountSel);
                    i.putExtra("fromBookings",1);
                    startActivity(i);
                }else{
                    Snackbar.make(concertbtn, "InSufficient Balance Booking Failed", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(),"InSufficient Balance Booking Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}