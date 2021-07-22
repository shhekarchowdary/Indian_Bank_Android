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
    public static int c=0,tm=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking_sub_menu);

        //setTitle is used to change title of the activity.
        setTitle("Banking");

        cb=findViewById(R.id.btnCB);
        tma=findViewById(R.id.btnTMA);
        toa=findViewById(R.id.btnTOA);
        cb.setOnClickListener(new onclick());
        tma.setOnClickListener(new onclick());
        toa.setOnClickListener(new onclick());

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