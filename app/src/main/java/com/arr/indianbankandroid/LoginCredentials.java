package com.arr.indianbankandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LoginCredentials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_credentials);
    }

    @Override
    public void onBackPressed() {
        Intent inn = new Intent(getBaseContext(),MainActivity.class);
        inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        inn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //inn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(inn);
        finish(); // finish the current activity
    }
}