package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button mLogin;
    ImageView mImageView;
    TextView mGetAccount;

    FirebaseDatabase rootNode;
    DatabaseReference referenceCustomers;
    DatabaseReference referenceAccounts;
    DatabaseReference referenceTransactions;

    public static ArrayList<Customer> mCustomers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogin = findViewById(R.id.btnLogin2);
        mImageView = findViewById(R.id.imageView);
        mGetAccount = findViewById(R.id.txtmgetAccountwithUs);
        rootNode = FirebaseDatabase.getInstance();
        referenceTransactions = rootNode.getReference("Transactions");

        int res = getResources().getIdentifier("logo2","drawable",getPackageName());
        mImageView.setImageResource(res);

        fillData();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        mGetAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(i);
            }
        });

    }

    public void fillData() {
        /*rootNode = FirebaseDatabase.getInstance();
        referenceCustomers = rootNode.getReference("Customers");
        Toast.makeText(getBaseContext(),String.valueOf(referenceCustomers),Toast.LENGTH_SHORT).show();
        referenceAccounts = rootNode.getReference("Accounts");

        Customer cus1 = new Customer("10105", "Lambton College", "Lambton", "14-06-1999"
                , "Public College", "9876788995", "support@lambton.ca", "North York",
                "Toronto", "AGDF45698h", "1234 3456 4567", "3456",
                "1234");
        referenceCustomers.child(cus1.getCin()).setValue(cus1);
        cus1.createAccount(1,"101051",6000,"","");
        //cus1.createAccount(2,"1010",90000,"","");
        cus1.createAccount(3,"101053",1000000,"Lambton solutions","LAM123");*/
        //String currentTime = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss z").format(new Date());
        //referenceTransactions.child(currentTime).setValue(new TransactionsHistory("101011","2021-02-01","Credit","Tranfer",100));
        //referenceTransactions.child(currentTime).setValue();
        /*referenceTransactions.setValue();
                            referenceTransactions.setValue();
                            referenceTransactions.setValue();
                            referenceTransactions.setValue(new TransactionsHistory("10101","2021-02-20","Credit","Tranfer",200));
                            referenceTransactions.setValue(new TransactionsHistory("10101","2021-02-15","Credit","Tranfer",222));
                            referenceTransactions.setValue(new TransactionsHistory("10101","2021-03-14","Credit","Tranfer",234));
                            referenceTransactions.setValue(new TransactionsHistory("10101","2021-03-25","Credit","Tranfer",456));
                            referenceTransactions.setValue(new TransactionsHistory("10101","2021-03-30","Credit","Tranfer",1200));
*/
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        moveTaskToBack(true);
    }
}