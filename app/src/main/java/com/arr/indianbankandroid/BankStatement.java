package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BankStatement extends AppCompatActivity {
    ArrayList<TransactionsHistory> tfh=new ArrayList<>();
    TextInputEditText startDate,endDate;
    Spinner userAccSpinner;
    ArrayList<String> accNames=new ArrayList<String>();
    Customer cusdata;
    ListView lv;
    BaseAdapter tempAdapter;
    Account tempA;
    Button searchHis;

    FirebaseDatabase rootNode;
    DatabaseReference referenceCustomers;
    DatabaseReference referenceTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_statement);
        startDate = findViewById(R.id.etvSDate);
        endDate = findViewById(R.id.etvEDate);
        userAccSpinner = findViewById(R.id.UserAcc);
        searchHis = findViewById(R.id.searchHis);
        cusdata=LoginActivity2.loggedInCustomer;

        rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        referenceTransactions = rootNode.getReference("Transactions");

        for(Account a:cusdata.getAccounts()){
            accNames.add(a.getType());
        }
        ArrayAdapter a = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,accNames);
        userAccSpinner.setAdapter(a);
        userAccSpinner.setOnItemSelectedListener(new spinnerEvents());

        lv=findViewById(R.id.HistoryDataList);
        if(tfh != null){
            tempAdapter = new HistoryDataAdapter(this,tfh);
            //set the adapter for the list view
            lv.setAdapter(tempAdapter);
            Log.d("listview","is not null");
        }
        else{
            Log.d("failed","array is null");
        }
        searchHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Selected Account Number",tempA.getAccountNo());
                String sdate = startDate.getText().toString().trim();
                String edate = endDate.getText().toString().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                ArrayList<TransactionsHistory> mTransactions = new ArrayList<>();
                Query checkTransactions = referenceTransactions.orderByChild("accountNo").equalTo(tempA.getAccountNo());
                checkTransactions.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            int numberOfTransactions = (int) snapshot.getChildrenCount();
                            //Toast.makeText(getBaseContext(),String.valueOf(numberOfAccounts),Toast.LENGTH_SHORT);
                            for(DataSnapshot d : snapshot.getChildren()){
                                TransactionsHistory fetchedTransaction = (TransactionsHistory) d.getValue(TransactionsHistory.class);
                                mTransactions.add(fetchedTransaction);
                                Log.d("No of in Array:",String.valueOf(mTransactions.size()));
                            }
                            try {
                                Date date1 = sdf.parse(sdate);
                                Date date2 = sdf.parse(edate);
                                Log.d("start Date:",String.valueOf(date1));
                                Log.d("End date:",String.valueOf(date2));
                                if(date1.before(new Date()))
                                    tfh.clear();
                                    for (TransactionsHistory th: mTransactions){
                                        Date d1 = sdf.parse(th.getTransferDate());
                                        if(d1.compareTo(date1) * d1.compareTo(date2) >= 0){
                                            Log.d("listview","data storing");
                                            tfh.add(th);
                                            tempAdapter.notifyDataSetChanged();
                                        }
                                    }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Log.d("No of Transactions:",String.valueOf(numberOfTransactions));
                        }else{
                            Toast.makeText(getApplicationContext(),"No Transactions Found",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Log.d("Line: 108",String.valueOf(mTransactions.size()));

            }
        });
    }

    private class spinnerEvents implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tfh.clear();
                for(Account a:cusdata.getAccounts()){
                    if(a.getType()==accNames.get(position)){
                        tempA = a;
                    }
                }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}