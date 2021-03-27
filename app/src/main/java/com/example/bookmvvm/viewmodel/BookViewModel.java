package com.example.bookmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookmvvm.db.Book;

import java.util.List;

public class BookViewModel extends AndroidViewModel {


    private BookRespository bookRespository;
    private final LiveData<List<Book>>  currentBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRespository = new BookRespository(application);
        currentBooks = bookRespository.get_allbooks();
    }
    public LiveData<List<Book>> getCurrentbooks(){
        return currentBooks;
    }


    public void save_book(Book book){

       bookRespository.save_book(book);
    }
    public void delete_book(Book book){

        bookRespository.delete_book(book);
    }
    public void delete_all(){

        bookRespository.delete_allbook();
    }
}
