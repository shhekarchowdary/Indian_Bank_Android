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

public class WaterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {


    EditText watersubscription,wateramount;
    int wsub,wamount;
    Spinner watersp;
    Customer loggedInCustomer;
    ArrayList<String> mAccounts = new ArrayList<>();
    int accountSel;
    Button waterpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        watersubscription = findViewById(R.id.watersubtv);
        wateramount = findViewById(R.id.wateramt);
        watersp = findViewById(R.id.waterspinner);
        waterpay = findViewById(R.id.waterpaybtn);

        loggedInCustomer = LoginActivity2.loggedInCustomer;
        mAccounts.clear();
        for(Account account:loggedInCustomer.getAccounts()){
            mAccounts.add(account.getType());
        }


        ArrayAdapter wateradp = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_dropdown_item,mAccounts);
        watersp.setAdapter(wateradp);
        watersp.setOnItemSelectedListener(this);


        waterpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!watersubscription.getText().toString().isEmpty()){
                    if(!wateramount.getText().toString().isEmpty()){
                        wsub = Integer.parseInt(watersubscription.getText().toString().trim());
                        wamount = Integer.parseInt(wateramount.getText().toString().trim());
                        int transId = loggedInCustomer.payBills(accountSel,wamount);
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