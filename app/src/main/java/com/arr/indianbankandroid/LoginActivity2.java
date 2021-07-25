package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class LoginActivity2 extends AppCompatActivity {
    TextInputEditText mPinNumber;
    Button mLoginFinal;
    ImageView mImageView3;

    ArrayList<Customer> mCustomers = MainActivity.mCustomers;
    public static Customer loggedInCustomer;
    private String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mLoginFinal = findViewById(R.id.txtLogin);
        mPinNumber = findViewById(R.id.etxtPassword);
        mImageView3 = findViewById(R.id.imageView3);


        int res = getResources().getIdentifier("logo2","drawable",getPackageName());
        mImageView3.setImageResource(res);

        mLoginFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInCustomer = null;
                pin = LoginActivity.pin;
                String pinNumber = mPinNumber.getText().toString().trim();
                if(!pinNumber.isEmpty()){
                    if(pin.equals(pinNumber)){
                        loggedInCustomer = LoginActivity.loggedInCustomer;
                        //break;
                    }
                    if(loggedInCustomer != null){
                        Intent i = new Intent(getBaseContext(),MainMenu.class);
                        startActivity(i);
                    }else
                        Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"Please Enter Pin Number",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        i.putExtra("from","pin");
        startActivity(i);
    }
}