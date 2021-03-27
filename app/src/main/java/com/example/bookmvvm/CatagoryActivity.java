package com.example.bookmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CatagoryActivity extends AppCompatActivity {
    CardView cardView1, cardView2, cardView3;
    Button addFromC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        addFromC = findViewById(R.id.addFromC);
        addFromC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(CatagoryActivity.this,AddFromCActivity.class);
                startActivity(intentAdd);
                finish();
            }
        });


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatagoryActivity.this, BookMainActivity.class);
                Toast.makeText(CatagoryActivity.this, "welcome to the world of words", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatagoryActivity.this, CdMainActivity.class);
                Toast.makeText(CatagoryActivity.this, "welcome to the world of music", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}
