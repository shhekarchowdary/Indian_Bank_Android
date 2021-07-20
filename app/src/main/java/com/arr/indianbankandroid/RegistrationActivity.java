package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText rName,rFname,rDob,rOccupation,rPnumber,rEmail,rAddress,rCity,rPan,rAdhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rName = findViewById(R.id.);
        rFname = findViewById(R.id.);
        rDob = findViewById(R.id.);
        rOccupation = findViewById(R.id.);
        rPnumber = findViewById(R.id.);
        rEmail = findViewById(R.id.);
        rAddress = findViewById(R.id.);
        rCity = findViewById(R.id.);
        rPan = findViewById(R.id.);
        rAdhar = findViewById(R.id.);

}