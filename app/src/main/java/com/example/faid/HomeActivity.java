package com.example.faid;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void profileName(View view) {
        TextInputEditText loginet = findViewById(R.id.email);
        String email = loginet.getText().toString();
        TextView profilenametv = findViewById(R.id.profilename);
        profilenametv.setText(email);
    }
}
