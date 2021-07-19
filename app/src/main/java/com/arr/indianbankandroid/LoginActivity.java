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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TextView mNext;
    TextInputEditText mAccessNumber;
    ImageView mImageView2;

    ArrayList<Customer> mCustomers = MainActivity.mCustomers;
    public static String accessCardNumber;
    String from;
    boolean accessCard;

    FirebaseDatabase rootNode;
    DatabaseReference referenceCustomers;
    DatabaseReference referenceAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNext = findViewById(R.id.txtNext);
        mAccessNumber = findViewById(R.id.etxtPassword);
        mImageView2 = findViewById(R.id.imageView2);

        rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        referenceAccounts = rootNode.getReference("Accounts");

        from = getIntent().getStringExtra("from");

        int res = getResources().getIdentifier("logo2","drawable",getPackageName());
        mImageView2.setImageResource(res);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accessNumber = mAccessNumber.getText().toString().trim();
                accessCard = false;
                if(!accessNumber.isEmpty()) {
                    Query checkUser = referenceCustomers.orderByChild("accessCardNumber").equalTo(accessNumber);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Intent i = new Intent(getBaseContext(), LoginActivity2.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Access Card Number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    /*for(Customer customer:mCustomers){
                        if(customer.getAccessCardNumber().equals(accessNumber)){
                            accessCardNumber = accessNumber;
                            accessCard = true;
                            break;
                        }
                     }*/
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