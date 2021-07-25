package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransferToOthersAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView toaAccountHolderName;
    EditText toaAmount,toaAccountNum;
    Button toafetch,toaTransfer;
    Spinner sptoaAccounts;
    Customer cusdata;
    Customer tempCus;
    String fetchedCustomerCin;
    String fetchedAccountNo;
    String fetchedCustomerName = "Hai";
    double fetchedAccountBalance;
    ArrayList<Customer> mCustomers=MainActivity.mCustomers;
    ArrayList<String> accName = new ArrayList<>();
    public static Account tempacc,toTempAcc;
    public static  int ts=0;
    boolean flag = false;
    int ch=0;

    boolean singleTime = false;
    FirebaseDatabase rootNode;
    DatabaseReference referenceCustomers;
    DatabaseReference referenceAccounts;
    DatabaseReference referenceTransactions;

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
        cusdata=LoginActivity2.loggedInCustomer;

        rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        referenceAccounts = rootNode.getReference("Accounts");
        referenceTransactions = rootNode.getReference("Transactions");

        for(Account a:cusdata.getAccounts()){
            accName.add(a.getType());
        }

        ArrayAdapter a = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,accName);
        sptoaAccounts.setAdapter(a);
        sptoaAccounts.setOnItemSelectedListener(this);
        toafetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch = 1;
                String g = toaAccountNum.getText().toString();
                if (!g.isEmpty()) {
                    fetchedCustomerName = null;
                    toaAccountHolderName.setText("");
                    Query checkAccounts = referenceAccounts.orderByChild("accountNo").equalTo(g);
                    checkAccounts.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                int accs = 0;
                                for(DataSnapshot d: snapshot.getChildren()){
                                    if(d.child("accountNo").getValue(String.class).equals(g)){
                                        accs = (int) d.getChildrenCount();
                                        fetchedAccountNo = d.child("accountNo").getValue(String.class);
                                        fetchedCustomerName = d.child("customerName").getValue(String.class);
                                        fetchedCustomerCin = d.child("cin").getValue(String.class);
                                        fetchedAccountBalance = d.child("currentBalance").getValue(Double.class);
                                        toaAccountHolderName.setText("Beneficiary Name: "+fetchedCustomerName);
                                        Log.d("after Query",String.valueOf(accs));
                                        break;
                                    }
                                }
                                if(accs == 0){
                                    Snackbar.make(toafetch, "No Accounts Found with given Number", Snackbar.LENGTH_LONG).show();
                                    //Toast.makeText(getApplicationContext(), "No Accounts Found", Toast.LENGTH_SHORT).show();
                                    toaAccountHolderName.setText("");
                                }
                            }else{
                                Log.d("No Account Foung","");
                                Snackbar.make(toafetch, "No Accounts Found with given Number", Snackbar.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), "No Accounts Found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else {
                    Snackbar.make(toafetch, "please enter account number to fetch details", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(), "please enter account number to fetch details", Toast.LENGTH_LONG).show();
                }
            }
        });

        toaTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch==1) {
                    if (!(toaAmount.getText().toString()).isEmpty() || !(toaAccountNum.getText().toString()).isEmpty()) {
                        if (fetchedAccountNo != null) {
                            if (!fetchedAccountNo.equals(tempacc.getAccountNo())){
                                Log.d(fetchedAccountNo,tempacc.getAccountNo());
                                double am = 0;
                                try {
                                    am = Double.parseDouble(toaAmount.getText().toString());
                                    if(am <= tempacc.getCurrentBalance()) {
                                        double value = tempacc.getCurrentBalance() - am;
                                        tempacc.setCurrentBalance(value);
                                        String date = new SimpleDateFormat("yyyy-MM-DD").format(new Date());
                                        TransactionsHistory transac = new TransactionsHistory(tempacc.getAccountNo(),date,"Debit","Transfered to "+ (fetchedAccountNo),am);
                                        tempacc.getTransferHis().add(transac);
                                        referenceAccounts.child(tempacc.getAccountNo()).child("currentBalance").setValue(value);
                                        String currentTime = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss z").format(new Date());
                                        referenceTransactions.child(currentTime+transac.getAccountNo()).setValue(transac);
                                        double transferValue = fetchedAccountBalance + am;
                                        TransactionsHistory creditTran = new TransactionsHistory(fetchedAccountNo,date,"Credit","Received from "+(tempacc.getAccountNo()),am);
                                        referenceAccounts.child(fetchedAccountNo).child("currentBalance").setValue(transferValue);
                                        String currentTime1 = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss z").format(new Date());
                                        referenceTransactions.child(currentTime1+creditTran.getAccountNo()).setValue(creditTran);
                                        Intent in = new Intent(getBaseContext(), Transcation_Success.class);
                                        startActivity(in);
                                        ts = 1;
                                    }
                                    else{
                                        Snackbar.make(toaTransfer, "Transaction Failed \n " +
                                                                   "Insufficient Balance", Snackbar.LENGTH_LONG).show();
                                        //Toast.makeText(getBaseContext(), "Transaction Failed \n Insufficient Balance", Toast.LENGTH_LONG).show();
                                    }
                                } catch (NumberFormatException e) {
                                    Snackbar.make(toaTransfer, "please enter amount", Snackbar.LENGTH_LONG).show();
                                    //Toast.makeText(getBaseContext(), "please enter amount", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Snackbar.make(toaTransfer, "Sender and Beneficiary should not be same", Snackbar.LENGTH_LONG).show();
                                //Toast.makeText(getBaseContext(), "Sender and Beneficiary should not be same", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Snackbar.make(toaTransfer, "Invalid Beneficiary Details", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getBaseContext(), "Invalid Beneficiary Details", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(toaTransfer, "please fill all details", Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(getBaseContext(), "please fill all details", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Snackbar.make(toaTransfer, "please click fetch details button to check account number", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(), "please click fetch details button to check account number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        for(Account acc:cusdata.getAccounts()){
            if(acc.getType() == accName.get(position)){
                tempacc = acc;
                Log.d("spinner account",acc.getType());
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}