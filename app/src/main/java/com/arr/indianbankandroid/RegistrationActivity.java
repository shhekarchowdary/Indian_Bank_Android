package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText rName, rFname, rDob, rOccupation, rPnumber, rEmail, rAddress, rCity, rPan, rAdhar;
    Button regBtn;


    int cin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rName = findViewById(R.id.textFullName);
        rFname = findViewById(R.id.textFatherName);
        rDob = findViewById(R.id.textDateOfBirth);
        rOccupation = findViewById(R.id.textOccupation);
        rPnumber = findViewById(R.id.textPhoneNumber);
        rEmail = findViewById(R.id.textEmail);
        rAddress = findViewById(R.id.textaddress);
        rCity = findViewById(R.id.textCity);
        rPan = findViewById(R.id.textPanNumber);
        rAdhar = findViewById(R.id.textAadharNumber);
        regBtn = findViewById(R.id.RegisterBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkDetailsEntered()){
                    fillData();
                }
            }
        });
    }

    boolean isEmpty(TextInputEditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDetailsEntered() {

        boolean allGood = true;

        if (isEmpty(rName)) {
            rName.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rFname)) {
            rFname.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rDob)) {
            rDob.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rOccupation)) {
            rOccupation.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rPnumber)) {
            rPnumber.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rEmail)) {
            rEmail.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rAddress)) {
            rAddress.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rCity)) {
            rCity.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rPan)) {
            rPan.setError("Should Not be Empty");
            allGood = false;
        }
        if (isEmpty(rAdhar)) {
            rAdhar.setError("Should Not be Empty");
            allGood = false;
        }
        return allGood;
    }

    public void fillData() {



        String fullName = rName.getText().toString();
        String fatherName = rFname.getText().toString();
        String DateofBirth = rFname.getText().toString();
        String Occupation = rFname.getText().toString();
        String PhoneNumber = rFname.getText().toString();
        String Email = rFname.getText().toString();
        String Address = rFname.getText().toString();
        String City = rFname.getText().toString();
        String Pan= rFname.getText().toString();
        String Aadhar = rFname.getText().toString();



        Customer cus1 = new Customer(String.valueOf(cin),fullName,fatherName,DateofBirth,Occupation,PhoneNumber,Email,Address,City,Pan,Aadhar,"","");


        //Toast.makeText(getBaseContext(),,Toast.LENGTH_LONG).show();

    }
}