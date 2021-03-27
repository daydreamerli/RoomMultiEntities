package com.example.bookmvvm.viewmodel;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.bookmvvm.db.BookDao;
import com.example.bookmvvm.db.Book;
import com.example.bookmvvm.db.BookDB;

import java.util.List;

public class BookRespository {

    private BookDB bookDB;
    private BookDao bookDao;
    private LiveData<List<Book>> mAllBooks;
    Context context;
    private static final String TAG = "BookRespository";

    public BookRespository(Context context) {

        bookDB = BookDB.getDBInstance(context);
        bookDao = bookDB.getBookDao();
        mAllBooks = bookDao.getAllBooks();
    }
    public LiveData<List<Book>> get_allbooks(){
        return mAllBooks;
    }


    public void save_book(Book book) {

        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.insert(book);
                    Log.d(TAG, "run : saved book => " + book.getBookTitle());
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to save book => " + book.getBookTitle() +
                            " because: " + e.getMessage() + "");
                }
            }
        });

    }

    public LiveData<Book> get_book(int id) {
        return bookDao.get_book(id);
    }

    public void update_book(Book book) {
        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.update(book);
                    Log.d(TAG, "run : update => " + book.getBookTitle() + "information");
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to update book => " + book.getBookTitle() +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    public void delete_book(Book book) {
        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.delete(book);
                    Log.d(TAG, "run : successfully delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete book => " +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }
    public void delete_allbook() {
        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.deleteAllBooks();
                    Log.d(TAG, "run : delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete books => "  +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }
}
