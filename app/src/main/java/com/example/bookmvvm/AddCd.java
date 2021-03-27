package com.example.bookmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.bookmvvm.adapter.CdAdapter;
import com.example.bookmvvm.db.Book;
import com.example.bookmvvm.db.Cd;
import com.example.bookmvvm.db.BookDB;
import com.example.bookmvvm.db.CdDao;
import com.example.bookmvvm.db.DataConverter;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCd extends AppCompatActivity {
    EditText editAlbum,editSinger;
    ImageView cdsrcImage;
    Bitmap bmpImage;
    Button buttonSave,buttonGoback;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    CdDao cdDao;
    CdAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cd);

        cdDao = BookDB.getDBInstance(this).getcdDao();

        editAlbum = findViewById(R.id.editAlbum);
        editSinger = findViewById(R.id.editSinger);
        cdsrcImage = findViewById(R.id.cdsrcImage);

        cdsrcImage.setOnClickListener(v -> openGallery());
        bmpImage = null;
        buttonGoback = findViewById(R.id.buttonGoback);
        buttonSave = findViewById(R.id.buttonSave);
        buttonGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCd.this, CdMainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            try {
                Bitmap bitmap = getImageBitmap(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getImageBitmap(Uri imageUri) throws FileNotFoundException {
        InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        cdsrcImage.setImageBitmap(selectedImage);
        bmpImage = selectedImage;
        return bmpImage;
    }


    public void onAddButtonClick(View view){
        if(editAlbum.getText().toString().isEmpty() ||
                editSinger.getText().toString().isEmpty()
        ){
            Toast.makeText(
                    this,
                    "Error: Input Data is missing",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Cd cd = new Cd();
            cd.setCdAlbumname(editAlbum.getText().toString());
            cd.setCdSinger(editSinger.getText().toString());
            cd.setImage(DataConverter.convertImage2ByteArray(bmpImage));
            cdDao.insert(cd);
            Log.d("CD",cd.getCdAlbumname()+"singed by: " +cd.getCdSinger()+"saved into database");
            Toast.makeText(
                    this,
                    "Insertion successful",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

}