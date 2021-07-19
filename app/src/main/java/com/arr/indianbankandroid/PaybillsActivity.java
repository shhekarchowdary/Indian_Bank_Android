package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaybillsActivity extends AppCompatActivity {

    Button hydrobutton,waterbutton,gasbutton,phonebutton,broadbandbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybills);
        hydrobutton = findViewById(R.id.hydrosubmenubtn);
        waterbutton = findViewById(R.id.watersubmenubtn);
        gasbutton = findViewById(R.id.gassubmenubtn);
        phonebutton = findViewById(R.id.phonesubmenubtn);
        broadbandbutton = findViewById(R.id.broadbandsubmenubtn);

        hydrobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hyd = new Intent(getBaseContext(),HydroActivity.class);
                startActivity(hyd);
            }
        });

        waterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wtr = new Intent(getBaseContext(),WaterActivity.class);
                startActivity(wtr);
            }
        });

        gasbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gs = new Intent(getBaseContext(),GasActivity.class);
                startActivity(gs);
            }
        });

        phonebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ph = new Intent(getBaseContext(),PhoneActivity.class);
                startActivity(ph);
            }
        });

        broadbandbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent brd = new Intent(getBaseContext(),BroadBandActivity.class);
                startActivity(brd);
            }
        });
    }
}