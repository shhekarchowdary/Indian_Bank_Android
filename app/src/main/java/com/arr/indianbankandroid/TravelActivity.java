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

public class TravelActivity extends AppCompatActivity {

    EditText travelsourse, traveldestination, traveldate;
    Spinner travelspinnner;
    Button travelbtn, getbtn;
    RadioGroup travelradiogroup;
    RadioButton flight, train, bus;
    TextView mTravelTotal;

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
        setContentView(R.layout.activity_travel);

        travelsourse = findViewById(R.id.Sourceet);
        traveldestination = findViewById(R.id.destinationet);
        traveldate = findViewById(R.id.dateet);
        travelspinnner = findViewById(R.id.bookticketsspinner);
        travelbtn = findViewById(R.id.booktcktbtn);
        travelradiogroup = findViewById(R.id.travelradiogrp);
        flight = findViewById(R.id.flightbtn);
        train = findViewById(R.id.trainbtn);
        bus = findViewById(R.id.busbtn);
        mTravelTotal = findViewById(R.id.txtTravelTotal);
        getbtn = findViewById(R.id.gettcktbtn);

        rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        referenceAccounts = rootNode.getReference("Accounts");
        referenceTransactions = rootNode.getReference("Transactions");

        loggedInCustomer = LoginActivity2.loggedInCustomer;
        mAccounts.clear();
        for (Account account : loggedInCustomer.getAccounts()) {
            mAccounts.add(account.getType());
        }

        ArrayAdapter spinAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, mAccounts);
        travelspinnner.setAdapter(spinAdapter);

        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!travelsourse.getText().toString().trim().isEmpty()){
                    if(!traveldestination.getText().toString().trim().isEmpty()){
                        if(!traveldate.getText().toString().trim().isEmpty()){
                            if(radioClicked){
                                payment = loggedInCustomer.bookings(2,1);
                                mTravelTotal.setText(Double.toString(payment));
                            }else
                                Snackbar.make(getbtn, "Select Mode of Transport", Snackbar.LENGTH_LONG).show();
                                //Toast.makeText(getBaseContext(),"Select Mode of Transport",Toast.LENGTH_LONG).show();
                        }else
                            Snackbar.make(getbtn, "Enter Date", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getBaseContext(),"Enter Date",Toast.LENGTH_LONG).show();
                    }else
                        Snackbar.make(getbtn, "Enter Destination City", Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(getBaseContext(),"Enter Destination City",Toast.LENGTH_LONG).show();
                }else
                    Snackbar.make(getbtn, "Enter Source City", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(),"Enter Source City",Toast.LENGTH_LONG).show();
            }
        });

        travelspinnner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        travelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                Account account = loggedInCustomer.getAccount(accountSel);
                if(payment < account.getCurrentBalance()){
                    double value = account.getCurrentBalance() - payment;
                    account.setCurrentBalance(value);
                    referenceAccounts.child(account.getAccountNo()).child("currentBalance").setValue(value);
                    String date = new SimpleDateFormat("yyyy-MM-DD").format(new Date());
                    TransactionsHistory transac = new TransactionsHistory(account.getAccountNo(),date,"Debit","For Travel Bookings",payment);
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
                    Snackbar.make(travelbtn, "InSufficient Balance Booking Failed", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(),"InSufficient Balance Booking Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
