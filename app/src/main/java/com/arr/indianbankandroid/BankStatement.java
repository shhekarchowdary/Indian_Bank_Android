package com.arr.indianbankandroid;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BankStatement extends AppCompatActivity {
    ArrayList<TransactionsHistory> tfh=new ArrayList<>();
    EditText startDate,endDate;
    Spinner userAccSpinner;
    ArrayList<String> accNames=new ArrayList<String>();
    Customer cusdata;
    ListView lv;
    BaseAdapter tempAdapter;
    Account tempA;
    Button searchHis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_statement);
        startDate = findViewById(R.id.etvSDate);
        endDate = findViewById(R.id.etvEDate);
        userAccSpinner = findViewById(R.id.UserAcc);
        searchHis = findViewById(R.id.searchHis);
        cusdata=LoginActivity2.loggedInCustomer;
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
                String sdate = startDate.getText().toString().trim();
                String edate = endDate.getText().toString().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date1 = sdf.parse(sdate);
                    Date date2 = sdf.parse(edate);
                    if(date1.equals(sdf.format(new Date())) )
                    for (TransactionsHistory th: tempA.getTransferHis()){
                        Date d1 = sdf.parse(th.getTransferDate());
                        if(d1.equals(date1) && d1.equals(date2)){
                            Log.d("listview","data storing");
                            tfh.add(th);
                            tempAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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