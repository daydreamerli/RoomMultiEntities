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

import com.example.bookmvvm.adapter.BookAdapter;
import com.example.bookmvvm.db.Book;

import com.example.bookmvvm.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;



public class BookMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addButton,gobackFromBook;

    BookAdapter adapter;
    public  static final String DB_Name = "book_db";

    private BookViewModel bookViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmain);

        List<Book> bookList = new ArrayList<>();

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BookAdapter(bookList,this);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);   //   ? ?  ?
        bookViewModel.getCurrentbooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                        // update the cached copy of books in the adapter
                adapter.setBook(books);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setonItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Book obj, int pos) {
                Intent intent = new Intent(BookMainActivity.this,BookDetailsActivity.class);
                intent.putExtra("book_id",obj.getId()+"");
                Log.d("book", "onItemClick choosen "+obj.getId());
                Log.d("book", "onItemClick choosen "+obj.getBookTitle());
                BookMainActivity.this.startActivity(intent);
            }

        });

        gobackFromBook = findViewById(R.id.gobackFromBook);
        gobackFromBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCata = new Intent(BookMainActivity.this, CatagoryActivity.class);
                startActivity(intentCata);
                finish();
            }
        });
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(BookMainActivity.this,AddBook.class);
                startActivity(intentAdd);
                finish();
            }
        });

    }



}