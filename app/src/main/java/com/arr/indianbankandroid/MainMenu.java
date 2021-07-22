package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    Button banking,paybills,cusOverView,bookings,logout;
    TextView cusName;
    Customer cusdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //setTitle is used to change title of the activity.
        setTitle("Main Menu");
        cusName=findViewById(R.id.tvCusName);
        banking=findViewById(R.id.btnBanking);
        paybills=findViewById(R.id.btnPayBills);
        cusOverView=findViewById(R.id.btnCusOverview);
        bookings=findViewById(R.id.btnBookings);
        logout=findViewById(R.id.btnLogOut);

        cusdata = LoginActivity2.loggedInCustomer;
        Log.d("logincheck",cusdata.getFullName());
        cusName.setText("Welcome "+cusdata.getFullName().toString());


        banking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), BankingSubMenu.class);
                startActivity(myIntent);

            }
        });
        paybills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payint = new Intent(getBaseContext(),PaybillsActivity.class);
                startActivity(payint);

            }
        });
        cusOverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),OverView.class);
                startActivity(i);
            }
        });
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent book = new Intent(getBaseContext(),BookingsActivity.class);
                startActivity(book);


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggingout();
            }
        });

    }

    private void loggingout() {
        Intent inn = new Intent(getBaseContext(),LoginActivity.class);
        inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        inn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(inn);
        finish(); // finish the current activity
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        i.putExtra("from","main");
        startActivity(i);

    }



}