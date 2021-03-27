package com.example.bookmvvm.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM BookTable")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM BookTable WHERE id = :id")
    LiveData<Book> get_book(int id);

    @Insert
    void insert(Book books);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM BookTable")
    void deleteAllBooks();


}