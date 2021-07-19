package com.arr.indianbankandroid;

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

public class LoginActivity extends AppCompatActivity {

    TextView mNext;
    TextInputEditText mAccessNumber;
    ImageView mImageView2;

    ArrayList<Customer> mCustomers = MainActivity.mCustomers;
    public static String accessCardNumber;
    String from;

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
                String accessNumber = mAccessNumber.getText().toString().trim();
                boolean accessCard = false;
                if(!accessNumber.isEmpty()){
                    for(Customer customer:mCustomers){
                        if(customer.getAccessCardNumber().equals(accessNumber)){
                            accessCardNumber = accessNumber;
                            accessCard = true;
                            break;
                        }
                    }
                    if(accessCard){
                        Intent i = new Intent(getBaseContext(),LoginActivity2.class);
                        startActivity(i);
                    }else
                        Toast.makeText(getApplicationContext(),"Wrong Access Card Number",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"Please Enter Access Card Number",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}