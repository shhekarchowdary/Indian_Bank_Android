package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    ImageButton banking,paybills,bookings,logout;
    Button cusOverView,checkBalance,transfer,others,hydro,water,gas,phone,broadband,travel,movies,liveconcert;
    TextView cusName;
    Customer cusdata;
    public static int c1=0,tm1=0,t1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //setTitle is used to change title of the activity.
        setTitle("Main Menu");
       // cusName=findViewById(R.id.tvCusName);
        banking=findViewById(R.id.btnBanking);
        paybills=findViewById(R.id.btnTMA);
        cusOverView=findViewById(R.id.btnCustomerOverview);
        bookings=findViewById(R.id.btnBookings);
        logout=findViewById(R.id.btnLogout);
        checkBalance=findViewById(R.id.btnCheckBalance);
        transfer=findViewById(R.id.btnTransfer);
        others=findViewById(R.id.btnOthers);
        hydro=findViewById(R.id.btnHydroBill);
        water=findViewById(R.id.btnWaterBill);
        gas=findViewById(R.id.btnGasBill);
        phone=findViewById(R.id.btnPhoneBill);
        broadband=findViewById(R.id.btnBroadbandBill);
        travel=findViewById(R.id.btnTravel);
        movies=findViewById(R.id.btnMovieTickets);
        liveconcert=findViewById(R.id.btnLiveConcert);

        cusdata = LoginActivity2.loggedInCustomer;
        Log.d("logincheck",cusdata.getFullName());
        //cusName.setText("Welcome "+cusdata.getFullName().toString());


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

        checkBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1=1;
                tm1=0;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tm1=1;
                c1=0;
                t1=1;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), TransferToOthersAccountActivity.class);
                startActivity(myIntent);
            }
        });

        hydro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), HydroActivity.class);
                startActivity(myIntent);
            }
        });

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), WaterActivity.class);
                startActivity(myIntent);
            }
        });

        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), GasActivity.class);
                startActivity(myIntent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), PhoneActivity.class);
                startActivity(myIntent);
            }
        });

        broadband.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), BroadBandActivity.class);
                startActivity(myIntent);
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), TravelActivity.class);
                startActivity(myIntent);
            }
        });

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), MovieActivity.class);
                startActivity(myIntent);
            }
        });

        liveconcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), LiveConcertActivity.class);
                startActivity(myIntent);
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
        Toast.makeText(getBaseContext(),"Press Logout Button for Logout",Toast.LENGTH_LONG).show();
    }



}