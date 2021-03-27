package com.example.bookmvvm;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmvvm.adapter.CdAdapter;


import com.example.bookmvvm.db.Cd;
import com.example.bookmvvm.viewmodel.CdViewModel;

import java.util.ArrayList;
import java.util.List;

public class CdMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addcdButton,gobackFromCD;

    CdAdapter adapter;
    public  static final String DB_Name = "book_db";

    private CdViewModel cdViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd_main);
        List<Cd> cdList = new ArrayList<>();

        recyclerView = findViewById(R.id.rcViewCd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CdAdapter(cdList,this);

        cdViewModel = new ViewModelProvider(this).get(CdViewModel.class);        //
        cdViewModel.getCurrentCds().observe(this, new Observer<List<Cd>>() {
            @Override
            public void onChanged(List<Cd> cds) {
                // update the cached copy of cds in the adapter
                adapter.setCdList(cds);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setonItemClickListener(new CdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Cd obj, int pos) {
                Intent intent = new Intent(CdMainActivity.this,CdDetailsActivity.class);
                intent.putExtra("cd_id",obj.getId()+"");
                Log.d("cd", "onItemClick choosen "+obj.getId());
                Log.d("cd", "onItemClick choosen "+obj.getCdSinger());
                CdMainActivity.this.startActivity(intent);
                finish();
            }

        });

        gobackFromCD = findViewById(R.id.gobackFromCD);
        gobackFromCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentC = new Intent(CdMainActivity.this, CatagoryActivity.class);
                startActivity(intentC);
                finish();
            }
        });
        addcdButton = findViewById(R.id.addcdButton );
        addcdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(CdMainActivity.this,AddCd.class);
                startActivity(intentAdd);
                finish();
            }
        });

    }

}
