package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView mNext;
    TextInputEditText mAccessNumber;
    ImageView mImageView2;

    ArrayList<Customer> mCustomers = MainActivity.mCustomers;
    public static Customer loggedInCustomer;
    public static String pin;
    String from;
    boolean accessCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNext = findViewById(R.id.txtNext);
        mAccessNumber = findViewById(R.id.etxtPassword);
        mImageView2 = findViewById(R.id.imageView2);



        from = getIntent().getStringExtra("from");

        int res = getResources().getIdentifier("logo2","drawable",getPackageName());
        mImageView2.setImageResource(res);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cinNumber = Objects.requireNonNull(mAccessNumber.getText()).toString().trim();
                accessCard = false;
                if(!cinNumber.isEmpty()) {

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}