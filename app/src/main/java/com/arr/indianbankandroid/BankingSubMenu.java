package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BankingSubMenu extends AppCompatActivity {
    ImageButton cb,tma,toa;
    Button savingsbtn,savingsprobtn,salarybtn,trsavingsbtn,trsavingsprobtn,trsalarybtn,transferNow,accountStatements;
    public static int c=0,tm=0;
    public static int selected = 0;
    public static int trselected = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking_sub_menu);

        //setTitle is used to change title of the activity.
        setTitle("Banking");

        cb=findViewById(R.id.btnCB);
        tma=findViewById(R.id.btnTMA);
        toa=findViewById(R.id.btnTOA);
        savingsbtn = findViewById(R.id.btnsavings);
        savingsprobtn = findViewById(R.id.btnsavingspro);
        salarybtn = findViewById(R.id.btnsalary);
        trsavingsbtn = findViewById(R.id.btnsavings1);
        trsavingsprobtn = findViewById(R.id.btnsavingspro1);
        trsalarybtn = findViewById(R.id.btnsalary1);
        transferNow = findViewById(R.id.btntransfernow);
        accountStatements = findViewById(R.id.btnStatement);

        cb.setOnClickListener(new onclick());
        tma.setOnClickListener(new onclick());
        toa.setOnClickListener(new onclick());

        savingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=1;
                tm=0;
                selected = 0;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);

            }
        });

        savingsprobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 1;
                c=1;
                tm=0;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);

            }
        });

        salarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 2;
                c=1;
                tm=0;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);

            }
        });

        trsavingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tm=1;
                c=0;
                trselected = 0;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);

            }
        });

        trsavingsprobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tm=1;
                c=0;
                trselected = 1;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);

            }
        });

        trsalarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tm=1;
                c=0;
                trselected = 2;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);

            }
        });

        transferNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), TransferToOthersAccountActivity.class);
                startActivity(myIntent);
            }
        });

        accountStatements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), BankStatement.class);
                startActivity(myIntent);
            }
        });

    }
    private class onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnCB)
            {
                c=1;
                tm=0;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);
                Log.d("check","check");
            }
            else if (v.getId()==R.id.btnTMA)
            {
                tm=1;
                c=0;
                Intent myIntent = new Intent(getBaseContext(), BankTranscations.class);
                startActivity(myIntent);
                Log.d("check","transfer");
            }
            else if (v.getId()==R.id.btnTOA)
            {
                Intent myIntent = new Intent(getBaseContext(), TransferToOthersAccountActivity.class);
                startActivity(myIntent);
            }
        }
    }




}