package com.example.bookmvvm;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookmvvm.db.Book;
import com.example.bookmvvm.db.DataConverter;
import com.example.bookmvvm.viewmodel.BookRespository;

import java.util.Observable;


public class UpdateBookActivity extends AppCompatActivity {
    Intent intent;
    String book_id = "";
    BookRespository bookRespository;
    Context context;
    ImageView updatebookImage;
    EditText updateTitle, updateAuthor;
    Button buttonUpdate,buttoncancleUpdate;
    Book book;

    private void get_book() {
        bookRespository.get_book(Integer.valueOf(book_id+"")).observe(this, new Observer<Book>() {

            @Override
            public void onChanged(Book book_choosed) {
                if(book_choosed == null){
                    Toast.makeText(UpdateBookActivity.this,"Book not fund in DB",Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                book = book_choosed;
                Log.d("book title", book.getBookTitle());
                populate_data();
            }

        });
    }
    private void populate_data() {
        updateTitle = findViewById(R.id.updateTitle);
        updateAuthor = findViewById(R.id.updateAuthor);
        updatebookImage = findViewById(R.id.updatebookImage);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        updateTitle.setText(book.getBookTitle());
        updateAuthor.setText(book.getBookAuthor());
        updatebookImage.setImageBitmap(DataConverter.converByteArray2Image(book.getImage()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        intent = getIntent();
        book_id = intent.getStringExtra("edit_book");
        Log.d("bookid", book_id+"choosed");
        bookRespository = new BookRespository(this);
        get_book();

//        updateTitle.addTextChangeListener(new TextWatcher){
//
//        }
        buttoncancleUpdate= findViewById(R.id.buttoncancleUpdate);
        buttoncancleUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentC = new Intent(UpdateBookActivity.this,BookMainActivity.class);
//                intentC.putExtra("book_id",book_id);
//                Log.d("book", "onItemClick choosen "+book_id);
                startActivity(intentC);
                finish();
            }
        });


    }
}