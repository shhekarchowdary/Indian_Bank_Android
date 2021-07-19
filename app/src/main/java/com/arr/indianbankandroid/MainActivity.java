package com.arr.indianbankandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mLogin;
    ImageView mImageView;

    public static ArrayList<Customer> mCustomers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogin = findViewById(R.id.btnLogin);
        mImageView = findViewById(R.id.imageView);

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
    }

    public void fillData() {
        mCustomers.add(new Customer("10101", "Soma Sekhar Anaparthi", "Ramarao Anaparthi", "13-01-1996"
                , "Business", 8989898989L, "shhekar@gmail.com", "katheru, Rajahmundry",
                "Rajahmundry", "ASDF45678h", "1234 1234 1234", "1234",
                "1234"));
        mCustomers.add(new Customer("10102", "Manikanta", "Soma sekhar", "13-02-1998"
                , "Business", 787878787878L, "manikanta@gmail.com", "Morampudi, Rajahmundry",
                "Rajahmundry", "ASDF45678G", "4567 4567 4567", "2345234523452345",
                "2345"));
        mCustomers.add(new Customer("10107", "Dhanush", "Jithendra", "13-03-1997"
                , "Business", 6767676767L, "dhanush@gmail.com", "ponnur, Guntur",
                "Guntur", "ASDF45678Y", "6767 6767 6767 6767", "4545454545454545",
                "4545"));
        mCustomers.add(new Customer("10110", "Jithendra", "Manikanta", "13-04-1995"
                , "Electrical Engineer", 99999999999L, "jithandra@gmail.com", "2-34, Ananthapur",
                "Ananthapur", "ASDF45678U", "8989 8989 8989", "5678567856785678",
                "5678"));
        for(Customer customer:mCustomers){
            if(customer.getCin().equals("10101")){
                customer.createAccount(1,"101011",1000,"","");
                customer.createAccount(2,"101012",10000,"","");
                customer.createAccount(3,"101013",5000,"XYZ solutions","XYZ123");
            }
            if(customer.getCin().equals("10102")){
                customer.createAccount(2,"101021",9000,"","");
                customer.createAccount(3,"101022",7000,"ABE solutions","ABE123");
            }
            if(customer.getCin().equals("10107")){
                customer.createAccount(1,"101071",3000,"","");
            }
            if(customer.getCin().equals("10110")){
                customer.createAccount(1,"101101",1000,"","");
                customer.createAccount(2,"101101",10000,"","");
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        moveTaskToBack(true);
    }
}