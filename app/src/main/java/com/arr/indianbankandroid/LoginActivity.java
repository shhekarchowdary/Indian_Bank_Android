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
                String cinNumber = Objects.requireNonNull(mAccessNumber.getText()).toString().trim();
                accessCard = false;
                if(!cinNumber.isEmpty()) {
                    Query checkUser = referenceCustomers.orderByChild("cin").equalTo(cinNumber);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String cinDB = snapshot.child(cinNumber).child("cin").getValue(String.class);
                                String fullNameDB = snapshot.child(cinNumber).child("fullName").getValue(String.class);
                                String fatherNameDB = snapshot.child(cinNumber).child("fatherName").getValue(String.class);
                                String dobDB = snapshot.child(cinNumber).child("dob").getValue(String.class);
                                String occupationDB = snapshot.child(cinNumber).child("occupation").getValue(String.class);
                                String phoneNumberDB = snapshot.child(cinNumber).child("phoneNumber").getValue(String.class);
                                String emailIdDB = snapshot.child(cinNumber).child("emailId").getValue(String.class);
                                String addressDB = snapshot.child(cinNumber).child("address").getValue(String.class);
                                String cityDB = snapshot.child(cinNumber).child("city").getValue(String.class);
                                String panNumberDB = snapshot.child(cinNumber).child("panNumber").getValue(String.class);
                                String aadharNumberDB = snapshot.child(cinNumber).child("aadharNumber").getValue(String.class);
                                String accessCardNumberDB = snapshot.child(cinNumber).child("accessCardNumber").getValue(String.class);
                                String pinNumberDB = snapshot.child(cinNumber).child("pinNumber").getValue(String.class);
                                pin = pinNumberDB;
                                Customer cus1 = new Customer(cinDB,fullNameDB,fatherNameDB,dobDB,occupationDB,phoneNumberDB,emailIdDB,addressDB,cityDB,panNumberDB,aadharNumberDB,accessCardNumberDB,pinNumberDB);
                                Query checkAccounts = referenceAccounts.child(cinDB).orderByChild("accountNo");
                                checkAccounts.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            int numberOfAccounts = (int) snapshot.getChildrenCount();
                                            for(DataSnapshot d : snapshot.getChildren()){
                                                String accountNo = d.child("accountNo").getValue(String.class);
                                                double currentBalance = d.child("currentBalance").getValue(Double.class);
                                                String type = d.child("type").getValue(String.class);
                                                if(type.equals("Savings Account")){
                                                    cus1.createAccount(1,accountNo,currentBalance,"","");
                                                }else if(type.equals("Savings Pro Account")){
                                                    cus1.createAccount(2,accountNo,currentBalance,"","");
                                                }else{
                                                    String companyName = d.child("companyName").getValue(String.class);
                                                    String empId = d.child("empId").getValue(String.class);
                                                    cus1.createAccount(1,accountNo,currentBalance,companyName,empId);
                                                }
                                            }
                                        }else{
                                            Toast.makeText(getApplicationContext(),"No Accounts Found",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                loggedInCustomer = cus1;
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