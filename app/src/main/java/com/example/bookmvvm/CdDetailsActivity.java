package com.example.bookmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmvvm.db.Cd;
import com.example.bookmvvm.db.DataConverter;
import com.example.bookmvvm.viewmodel.CdRespository;

public class CdDetailsActivity extends AppCompatActivity {
    Intent intent;
    String cd_id = "";
    CdRespository cdRespository;
    Context context;
    ImageView cd_imageview;
    TextView album_textview, singer_textview;
    Button button_edit;
    Cd cd;

//    private void delete_book() {
//        bookRespository.delete_book(book);
//        Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
//        finish();
//        return;
//    }


    private void get_cd() {
        cdRespository.get_cd(Integer.valueOf(cd_id+"")).observe(this, new Observer<Cd>() {

            @Override
            public void onChanged(Cd cd_choosed) {
                if(cd_choosed == null){
                    Toast.makeText(CdDetailsActivity.this,"CD not fund in DB",Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                cd = cd_choosed;
                populate_data();
            }

        });
    }

    private void populate_data() {
        album_textview = findViewById(R.id.album_textview);
        singer_textview = findViewById(R.id.singer_textview);
        cd_imageview = findViewById(R.id.cd_imageview);
//        button_edit = findViewById(R.id.button_edit);

//        button_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CdDetailsActivity.this,UpdateCdActivity.class);
//                intent.putExtra("edit_cd",book.getId());
//                startActivity(intent);
//            }
//        });
        album_textview.setText(cd.getCdAlbumname());
        singer_textview.setText(cd.getCdSinger());
        cd_imageview.setImageBitmap(DataConverter.converByteArray2Image(cd.getImage()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd_details);
        intent = getIntent();
        cd_id = intent.getStringExtra("cd_id");

        cdRespository = new CdRespository(this);
        get_cd();
    }
}