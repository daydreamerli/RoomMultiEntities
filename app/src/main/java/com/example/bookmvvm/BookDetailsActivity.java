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

import com.example.bookmvvm.db.Book;
import com.example.bookmvvm.db.DataConverter;
import com.example.bookmvvm.viewmodel.BookRespository;

public class BookDetailsActivity extends AppCompatActivity {
    Intent intent;
    String book_id = "";
    BookRespository bookRespository;
    Context context;
    ImageView image_detail;
    TextView title_detail, author_detail;
    Button button_edit;
    Book book;

//    private void delete_book() {
//        bookRespository.delete_book(book);
//        Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
//        finish();
//        return;
//    }


    private void get_book() {
        bookRespository.get_book(Integer.valueOf(book_id+"")).observe(this, new Observer<Book>() {

            @Override
            public void onChanged(Book book_choosed) {
                if(book_choosed == null){
                    Toast.makeText(BookDetailsActivity.this,"Book not fund in DB",Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                book = book_choosed;
                populate_data();
            }

        });
    }

    private void populate_data() {
        title_detail = findViewById(R.id.title_detail);
        author_detail = findViewById(R.id.author_detail);
        image_detail = findViewById(R.id.image_detail);
        button_edit = findViewById(R.id.button_edit);

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailsActivity.this,UpdateBookActivity.class);
                intent.putExtra("edit_book",book.getId()+"");
                startActivity(intent);
            }
        });
        title_detail.setText(book.getBookTitle());
        author_detail.setText(book.getBookAuthor());
        image_detail.setImageBitmap(DataConverter.converByteArray2Image(book.getImage()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        intent = getIntent();
        book_id = intent.getStringExtra("book_id");

        bookRespository = new BookRespository(this);
        get_book();
    }
}