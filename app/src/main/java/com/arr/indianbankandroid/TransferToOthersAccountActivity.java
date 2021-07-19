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

public class TransferToOthersAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView toaAccountHolderName,toaAccountCellNum;
    EditText toaAmount,toaAccountNum;
    Button toafetch,toaTransfer;
    Spinner sptoaAccounts;
    Customer cusdata;
    Customer tempCus;
    ArrayList<Customer> mCustomers=MainActivity.mCustomers;
    ArrayList<String> accName = new ArrayList<>();
    public static Account tempacc,toTempAcc;
    public static  int ts=0;
    boolean flag = true;
    int ch=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_others_account);
        setTitle("Transfer to Other Accounts");

        sptoaAccounts = findViewById(R.id.spTOAAccounts);
        toaAmount = findViewById(R.id.etvTOAAmount);
        toaAccountNum = findViewById(R.id.etvTOAAccountNum);
        toafetch = findViewById(R.id.btnFetchAccount);
        toaTransfer = findViewById(R.id.btnTOATransfer);
        toaAccountHolderName=findViewById(R.id.tvAccountHolderName);
        toaAccountCellNum=findViewById(R.id.tvAccountCellNum);
        cusdata=LoginActivity2.loggedInCustomer;

        accName.clear();
        for(Account a:cusdata.getAccounts()){
            accName.add(a.getType());
        }

        ArrayAdapter a = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,accName);
        sptoaAccounts.setAdapter(a);
        sptoaAccounts.setOnItemSelectedListener(this);
        toafetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch=1;
                String g = toaAccountNum.getText().toString();
                if(!g.isEmpty()){
                    int accNum =0;
                    try {
                        accNum = Integer.parseInt(toaAccountNum.getText().toString());
                        Log.d("Amount",String.valueOf(accNum));
                        for(Customer cus:mCustomers){
                            for(Account accdata:cus.getAccounts()){
                                if(Integer.parseInt(accdata.getAccountNo())==accNum){
                                    if(cusdata.getCin()==cus.getCin()){
                                        Toast.makeText(getBaseContext(),"You have entered your account number.Please enter Beneficary account number",Toast.LENGTH_LONG).show();
                                        flag = false;
                                    }
                                    else {
                                        toTempAcc = accdata;
                                        toaAccountHolderName.setText("Beneficiary Name: "+cus.getFullName().toString());
                                        toaAccountCellNum.setText("Phone Number: "+String.valueOf(cus.getPhoneNumber()).toString());
                                        flag = false;
                                    }
                                }
                            }
                        }
                        if(flag){
                            Toast.makeText(getBaseContext(),"Customer not found,Please check the account number",Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getBaseContext(),"please enter correct account number",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getBaseContext(),"please enter account number to fetch details",Toast.LENGTH_LONG).show();
                }

            }
        });

        toaTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch==1) {
                    if (!(toaAmount.getText().toString()).isEmpty() || !(toaAccountNum.getText().toString()).isEmpty()) {
                        if (flag == false) {
                            double am = 0;
                            try {
                                am = Double.parseDouble(toaAmount.getText().toString());
                                if(am<=tempacc.getCurrentBalance()) {
                                    double value = tempacc.getCurrentBalance() - am;
                                    tempacc.setCurrentBalance(value);
                                    double transferValue = toTempAcc.getCurrentBalance() + am;
                                    toTempAcc.setCurrentBalance(transferValue);
                                    Intent in = new Intent(getBaseContext(), Transcation_Success.class);
                                    startActivity(in);
                                    ts = 1;
                                }
                                else{
                                    Toast.makeText(getBaseContext(), "Transaction Failed \n Insufficient Balance", Toast.LENGTH_LONG).show();
                                }
                            } catch (NumberFormatException e) {
                                Toast.makeText(getBaseContext(), "please enter amount", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getBaseContext(), "Invalid Beneficiary Details", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "please fill all details", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getBaseContext(), "please click fetch details button to check account number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        for(Account acc:cusdata.getAccounts()){
            if(acc.getType()== accName.get(position)){
                tempacc = acc;
                Log.d("spinner account",acc.getType());
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}