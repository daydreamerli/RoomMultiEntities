package com.example.bookmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookmvvm.adapter.BookAdapter;
import com.example.bookmvvm.db.Book;
import com.example.bookmvvm.db.BookDB;
import com.example.bookmvvm.db.BookDao;
import com.example.bookmvvm.db.DataConverter;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddBook extends AppCompatActivity {
    EditText editTitle,editAuthor;
    ImageView booksrcImage;
    Bitmap bmpImage;
    Button buttonAdd,cacleInput;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    BookDao bookDao;
    BookAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        bookDao = BookDB.getDBInstance(this).getBookDao();

        editTitle = findViewById(R.id.editTitle);
        editAuthor = findViewById(R.id.editAuthor);
        booksrcImage = findViewById(R.id.booksrcImage);

        booksrcImage.setOnClickListener(v -> openGallery());
        bmpImage = null;
        cacleInput = findViewById(R.id.cacleInput);
        cacleInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBook.this, BookMainActivity.class);
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
        booksrcImage.setImageBitmap(selectedImage);
        bmpImage = selectedImage;
        return bmpImage;
    }


    public void onAddButtonClick(View view){
        if(editTitle.getText().toString().isEmpty() ||
                editAuthor.getText().toString().isEmpty()
                ){
            Toast.makeText(
                    this,
                    "Error: User Data is missing",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Book book = new Book();
            book.setBookTitle(editTitle.getText().toString());
            book.setBookAuthor(editAuthor.getText().toString());
            book.setImage(DataConverter.convertImage2ByteArray(bmpImage));
            bookDao.insert(book);
            Toast.makeText(
                    this,
                    "Insertion successful",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }




}