package com.example.faid;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent  = getIntent();
        Bundle bundle = intent.getExtras();
        String data = bundle.getString("state","");
       if (data.equals("signup")){
          Snackbar.make(findViewById(android.R.id.content), "Sign Up Successful",Snackbar.LENGTH_SHORT).show();
       }
       else{
           Snackbar.make(findViewById(android.R.id.content), "Log In Successful",Snackbar.LENGTH_SHORT).show();
       }
    }

    public void profileName(View view) {
        TextInputEditText loginet = findViewById(R.id.email);
        String email = loginet.getText().toString();
        TextView profilenametv = findViewById(R.id.profilename);
        profilenametv.setText(email);
    }
}
