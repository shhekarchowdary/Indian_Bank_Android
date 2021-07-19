package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PhoneActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText phonesubscription,phoneamount;
    int phsub,phamt;
    Spinner phonesp;
    Customer loggedInCustomer;
    ArrayList<String> mAccounts = new ArrayList<>();
    int accountSel;
    Button phonepay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        phonesubscription = findViewById(R.id.phonesubtv);
        phoneamount = findViewById(R.id.phoneamt);
        phonesp = findViewById(R.id.phonespinner);
        phonepay = findViewById(R.id.phonebtn);

        loggedInCustomer = LoginActivity2.loggedInCustomer;
        mAccounts.clear();
        for(Account account:loggedInCustomer.getAccounts()){
            mAccounts.add(account.getType());
        }


        ArrayAdapter phoneadp = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_dropdown_item,mAccounts);
        phonesp.setAdapter(phoneadp);
        phonesp.setOnItemSelectedListener(this);


        phonepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!phonesubscription.getText().toString().isEmpty()){
                    if(!phoneamount.getText().toString().isEmpty()){
                        phsub = Integer.parseInt(phonesubscription.getText().toString().trim());
                        phamt = Integer.parseInt(phoneamount.getText().toString().trim());
                        int transId = loggedInCustomer.payBills(accountSel,phamt);
                        if(transId != 0){
                            Intent i = new Intent(getBaseContext(),Transcation_Success.class);
                            i.putExtra("transId",transId);
                            i.putExtra("payAccount",accountSel);
                            i.putExtra("fromPayBills",1);
                            startActivity(i);
                        }else{
                            Toast.makeText(getBaseContext(),"Transaction Declined Insufficient Balance",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getBaseContext(),"Enter Amount",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getBaseContext(),"Enter Subscription Number",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String accountSelected = mAccounts.get(position);
        if(accountSelected.equals("Savings Account")){
            accountSel = 1;
        }else if(accountSelected.equals("Savings Pro Account")){
            accountSel = 2;
        }else if(accountSelected.equals("Salary Account")){
            accountSel = 3;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        accountSel = 1;

    }
}