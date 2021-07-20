package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText rName, rFname, rDob, rOccupation, rPnumber, rEmail, rAddress, rCity, rPan, rAdhar;
    Button regBtn;

    FirebaseDatabase rootNode;
    DatabaseReference referenceCustomers;
    DatabaseReference referenceCin;
    String cin;

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
                checkDetailsEntered();
            }
        });
    }

    boolean isEmpty(TextInputEditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDetailsEntered() {
        if (isEmpty(rName)) {
            Toast t = Toast.makeText(this, "You must enter full name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rFname)) {
            Toast t = Toast.makeText(this, "You must enter father name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rDob)) {
            Toast t = Toast.makeText(this, "You must enter Date Of Birth to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rOccupation)) {
            Toast t = Toast.makeText(this, "You must enter Occupation to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rPnumber)) {
            Toast t = Toast.makeText(this, "You must enter Phone Number to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rEmail)) {
            Toast t = Toast.makeText(this, "You must enter Email to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rAddress)) {
            Toast t = Toast.makeText(this, "You must enter Address to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rCity)) {
            Toast t = Toast.makeText(this, "You must enter City to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rPan)) {
            Toast t = Toast.makeText(this, "You must enter Pan Number to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(rAdhar)) {
            Toast t = Toast.makeText(this, "You must enter Adhar Number to register!", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void fillData() {

        rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        referenceCin = rootNode.getReference("customerCount");

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
        referenceCin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cin = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Customer cus1 = new Customer(cin,fullName,fatherName,DateofBirth,Occupation,555555555,Email,Address,City,Pan,Aadhar,"","");
        referenceCustomers.child(cus1.getCin()).setValue(cus1);

        //Toast.makeText(getBaseContext(),,Toast.LENGTH_LONG).show();

    }
}