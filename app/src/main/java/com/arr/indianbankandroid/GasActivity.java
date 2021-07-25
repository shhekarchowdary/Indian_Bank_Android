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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class GasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText gassubscription,gasamount;
    int gsub,gamount;
    Spinner gassp;
    Customer loggedInCustomer;
    ArrayList<String> mAccounts = new ArrayList<>();
    Button gasbtn;
    int accountSel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        gassubscription = findViewById(R.id.gassubtv);
        gasamount = findViewById(R.id.gasamt);
        gassp = findViewById(R.id.gasspinner);
        gasbtn = findViewById(R.id.gaspaybtn);


        loggedInCustomer = LoginActivity2.loggedInCustomer;
        mAccounts.clear();
        for(Account account:loggedInCustomer.getAccounts()){
            mAccounts.add(account.getType());
        }



        ArrayAdapter gasadp = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_dropdown_item,mAccounts);
        gassp.setAdapter(gasadp);
        gassp.setOnItemSelectedListener(this);


        gasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!gassubscription.getText().toString().isEmpty()){
                    if(!gasamount.getText().toString().isEmpty()){
                        gsub = Integer.parseInt(gassubscription.getText().toString().trim());
                        gamount = Integer.parseInt(gasamount.getText().toString().trim());
                        int transId = loggedInCustomer.payBills(accountSel,gamount);
                        if(transId != 0){
                            Intent i = new Intent(getBaseContext(),Transcation_Success.class);
                            i.putExtra("transId",transId);
                            i.putExtra("payAccount",accountSel);
                            i.putExtra("fromPayBills",1);
                            startActivity(i);
                        }else{
                            Snackbar.make(gasbtn, "Transaction Declined Insufficient Balance", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getBaseContext(),"Transaction Declined Insufficient Balance",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Snackbar.make(gasbtn, "Please Enter Amount", Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(getBaseContext(),"Enter Amount",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Snackbar.make(gasbtn, "Enter Subscription Number", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(),"Enter Subscription Number",Toast.LENGTH_SHORT).show();
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