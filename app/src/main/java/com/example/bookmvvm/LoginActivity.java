package com.example.bookmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CatagoryActivity.class);
                Toast.makeText(LoginActivity.this,"Welcome to Golden Memeries",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }

}