package com.example.bookmvvm.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BookTable")
public class Book {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String bookTitle;
    private String bookAuthor;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;



    public int getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}


