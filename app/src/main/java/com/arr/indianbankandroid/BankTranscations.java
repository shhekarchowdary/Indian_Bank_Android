package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BankTranscations extends AppCompatActivity {
    TextView balance,balanceAmount,tvToAcc,tvFromAcc,tvAmount,tvAccount,tvBankTransTxt;
    EditText etvAmount;
    Button btnTransfer;
    Spinner spToAcc,spFromAcc;
    int c=0,tm=0, c1=0, tm1=0;
    Customer cusdata;
    ArrayList<String> accNames=new ArrayList<String>();
    int enteredAmount,fromaccty,toaccty;
    double bal=0.0;
    double amount=0;
    double trfToAcc=0;
    public static Account fromaccdata;

    int spinnerselected = BankingSubMenu.selected;
    int trspinnerselected = BankingSubMenu.trselected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transcations);

        balance=findViewById(R.id.tvBalance);
        balanceAmount=findViewById(R.id.tvBalanceAmount);
        tvToAcc=findViewById(R.id.tvToAcc);
        tvFromAcc=findViewById(R.id.tvFromAcc);
        tvAmount=findViewById(R.id.tvAmount);
        etvAmount=findViewById(R.id.etvAmount);
        btnTransfer=findViewById(R.id.btnTransfer);
        spToAcc=findViewById(R.id.spToAcc);
        spFromAcc=findViewById(R.id.spFromAcc);
        tvAccount=findViewById(R.id.tvAccount);
        tvBankTransTxt=findViewById(R.id.tvBankTranTxt);
        cusdata = LoginActivity2.loggedInCustomer;
        for(Account a:cusdata.getAccounts()){
            accNames.add(a.getType());
        }

        ArrayAdapter a = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,accNames);
        spFromAcc.setAdapter(a);
        spToAcc.setAdapter(a);
        spFromAcc.setOnItemSelectedListener(new spinnerEvents());
        spToAcc.setOnItemSelectedListener(new spinnerEvents());
        c= BankingSubMenu.c;
        tm= BankingSubMenu.tm;
        c1= MainMenu.c1;
        tm1= MainMenu.tm1;
        spFromAcc.setSelection(spinnerselected);
        spToAcc.setSelection(trspinnerselected);
        if(c==1 || c1==1){
            //setTitle is used to change title of the activity.
            setTitle("Check Balance");
            tvBankTransTxt.setText("Check Balance");
            tvAccount.setVisibility(View.VISIBLE);
            balance.setVisibility(View.VISIBLE);
            balanceAmount.setVisibility(View.VISIBLE);
            //Invisible

            tvAmount.setVisibility(View.INVISIBLE);
            etvAmount.setVisibility(View.INVISIBLE);
            btnTransfer.setVisibility(View.INVISIBLE);
            spToAcc.setVisibility(View.INVISIBLE);
            tvFromAcc.setVisibility(View.INVISIBLE);
            tvToAcc.setVisibility(View.INVISIBLE);

        }
        else if(tm==1 || tm1==1){
            //setTitle is used to change title of the activity.
            setTitle("Transfer Money to My Account");
            tvBankTransTxt.setText("Transfer Money to My Account");
            tvAmount.setVisibility(View.VISIBLE);
            etvAmount.setVisibility(View.VISIBLE);
            btnTransfer.setVisibility(View.VISIBLE);
            spToAcc.setVisibility(View.VISIBLE);
            tvFromAcc.setVisibility(View.VISIBLE);
            tvToAcc.setVisibility(View.VISIBLE);

            //Invisible
            balance.setVisibility(View.INVISIBLE);
            balanceAmount.setVisibility(View.INVISIBLE);
            tvAccount.setVisibility(View.INVISIBLE);
            Log.d("check","transfer1");
        }

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    amount = Double.parseDouble(etvAmount.getText().toString());
                    Log.d("amount",String.valueOf(amount));
                    if(fromaccty==toaccty){
                        Toast.makeText(getBaseContext(),"Sender and beneficiary accounts are same. please change",Toast.LENGTH_LONG).show();
                    }
                    else {
                        boolean t = cusdata.transferMoney(fromaccty, toaccty, amount);
                        if (t) {
                            Account toaccdata = cusdata.getAccount(toaccty);
                            fromaccdata = cusdata.getAccount(fromaccty);

                            Log.d("from acc transfer check", String.valueOf(fromaccdata.getCurrentBalance()));
                            Log.d("to acc transfer check", String.valueOf(toaccdata.getCurrentBalance()));
                            Intent i = new Intent(getBaseContext(), Transcation_Success.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getBaseContext(), "Transaction failed \n Insufficient Balance", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getBaseContext(),"please enter amount",Toast.LENGTH_LONG).show();
                }


            }
        });

    }


    private class spinnerEvents implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            if(parent.getId()==R.id.spFromAcc){
                for(Account a:cusdata.getAccounts()){
                    if(a.getType()==accNames.get(position)){
                        if(c==1){
                            balanceAmount.setText(String.valueOf(a.getCurrentBalance()));
                        }
                        else{
                            if(a.getType()=="Savings Account"){
                                fromaccty=1;
                            }
                            else if(a.getType()=="Savings Pro Account"){
                                fromaccty=2;
                                //Toast.makeText(getBaseContext(),"fromaccty=2",Toast.LENGTH_LONG).show();
                            }
                            else if(a.getType()=="Salary Account"){
                                fromaccty=3;
                            }
                            //bal=a.getCurrentBalance();
                        }
                    }
                    /*else {
                        Toast.makeText(getBaseContext(),"please select account type",Toast.LENGTH_LONG).show();
                    }*/
                }
            }
            else if (parent.getId()==R.id.spToAcc){
                for(Account a:cusdata.getAccounts()){
                    if(a.getType()==accNames.get(position)){
                        if(a.getType()=="Savings Account"){
                            toaccty=1;
                        }
                        else if(a.getType()=="Savings Pro Account"){
                            toaccty=2;
                        }
                        else if(a.getType()=="Salary Account"){
                            toaccty=3;
                            Toast.makeText(getBaseContext(),"toaccty=3",Toast.LENGTH_LONG).show();
                        }

                        trfToAcc = a.getCurrentBalance();
                    }
                    /*else {
                        Toast.makeText(getBaseContext(),"please select account type",Toast.LENGTH_LONG).show();
                    }*/
                }
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}