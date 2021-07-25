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
    boolean singleTime = false;

    int cin;
    String fullName;
    String fatherName;
    String DateofBirth;
    String Occupation;
    String PhoneNumber;
    String Email;
    String Address;
    String City;
    String Pan;
    String Aadhar;

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

        fullName = rName.getText().toString();
        fatherName = rFname.getText().toString();
        DateofBirth = rFname.getText().toString();
        Occupation = rFname.getText().toString();
        PhoneNumber = rFname.getText().toString();
        Email = rFname.getText().toString();
        Address = rFname.getText().toString();
        City = rFname.getText().toString();
        Pan= rFname.getText().toString();
        Aadhar = rFname.getText().toString();

        rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        referenceCin = rootNode.getReference("customerCount");


        referenceCin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Toast.makeText(getBaseContext(),"In Listener",Toast.LENGTH_SHORT).show();
                if(snapshot.exists()){
                    cin = snapshot.child("cinReference").getValue(Integer.class);
                    //cin = Integer.parseInt(cinRef);
                    cin += 2;
                    if(!singleTime){
                        sendData();
                    }

                }else{
                    //Toast.makeText(getBaseContext(),"Cant Fetch CIN Number",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Toast.makeText(getBaseContext(),,Toast.LENGTH_LONG).show();

    }
    public void sendData() {
        Customer cus1 = new Customer(String.valueOf(cin),fullName,fatherName,DateofBirth,Occupation,PhoneNumber,Email,Address,City,Pan,Aadhar,"","");
        referenceCustomers.child(cus1.getCin()).setValue(cus1);
        referenceCin.child("cinReference").setValue(cin);
        singleTime = true;
    }
}