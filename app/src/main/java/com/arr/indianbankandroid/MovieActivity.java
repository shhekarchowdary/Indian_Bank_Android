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

import java.util.ArrayList;
import java.util.Random;

public class MovieActivity extends AppCompatActivity {
    EditText movieseats,seats;
    Button moviebutton,moviefarebutton;
    Spinner moviespinner;
    RadioGroup movieradgroup;
    RadioButton twilight,lordofrings,homealone,fastnfurious;
    TextView moviegetfare;

    boolean radioClicked = false;
    Customer loggedInCustomer;
    ArrayList<String> mAccounts = new ArrayList<>();
    double payment;
    int transId,accountSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieseats = findViewById(R.id.noofseats);
        moviebutton = findViewById(R.id.bookbtn);
        movieradgroup = findViewById(R.id.movieradiogrp);
        twilight = findViewById(R.id.twilight1);
        lordofrings = findViewById(R.id.lordrings);
        homealone = findViewById(R.id.homealone1);
        fastnfurious = findViewById(R.id.fastnfurious1);
        moviespinner = findViewById(R.id.moviesp);
        moviegetfare = findViewById(R.id.moviegetfaretv);
        moviefarebutton = findViewById(R.id.moviefarebtn);
        seats = findViewById(R.id.noofseats);


        loggedInCustomer = LoginActivity2.loggedInCustomer;
        mAccounts.clear();
        for (Account account : loggedInCustomer.getAccounts()) {
            mAccounts.add(account.getType());
        }

        ArrayAdapter spinAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, mAccounts);
        moviespinner.setAdapter(spinAdapter);

        twilight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        lordofrings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        homealone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });

        fastnfurious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioClicked = true;
            }
        });


        moviefarebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seats.getText().toString().trim().isEmpty()){
                    if(radioClicked){
                        String no = seats.getText().toString().trim();
                        int num = Integer.parseInt(no);
                        payment = loggedInCustomer.bookings(1,num);
                        moviegetfare.setText(Double.toString(payment));
                    }else
                        Toast.makeText(getBaseContext(),"Select Movie",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getBaseContext(),"Enter Number of Seats",Toast.LENGTH_LONG).show();
            }
        });


        moviespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String accountSelected = mAccounts.get(position);
                if(accountSelected.equals("Savings Account")){
                    accountSel = 1;
                }else if(accountSelected.equals("Savings Pro Account")){
                    accountSel = 2;
                }else if(accountSelected.equals("Salary Account")){
                    accountSel = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                accountSel = 1;
            }
        });

        moviebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                Account account = loggedInCustomer.getAccount(accountSel);
                if(payment < account.getCurrentBalance()){
                    account.setCurrentBalance(account.getCurrentBalance() - payment);
                    int low = 11111;
                    int high = 99999;
                    transId =  r.nextInt(high - low) + low;
                    Intent i = new Intent(getBaseContext(),Transcation_Success.class);
                    i.putExtra("transId",transId);
                    i.putExtra("payAccount",accountSel);
                    i.putExtra("fromBookings",1);
                    startActivity(i);
                }else{
                    Toast.makeText(getBaseContext(),"InSufficient Balance Booking Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}