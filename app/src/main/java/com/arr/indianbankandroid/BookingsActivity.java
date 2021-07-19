package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingsActivity extends AppCompatActivity {

    Button travelbutton,moviebutton,livebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        travelbutton =findViewById(R.id.travelbtn);
        moviebutton = findViewById(R.id.moviebtn);
        livebutton = findViewById(R.id.concertsbtn);


        travelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trl = new Intent(getBaseContext(),TravelActivity.class);
                startActivity(trl);
            }
        });

        moviebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mve = new Intent(getBaseContext(),MovieActivity.class);
                startActivity(mve);
            }
        });

        livebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent live = new Intent(getBaseContext(),LiveConcertActivity.class);
                startActivity(live);
            }
        });
    }
}