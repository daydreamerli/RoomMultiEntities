package com.example.bookmvvm.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static com.example.bookmvvm.BookMainActivity.DB_Name;
@Database(entities = {Book.class,Cd.class},version = 3,exportSchema = false)
public abstract class BookDB extends RoomDatabase {

//    private  static final String DB_Name = "book_db";

    private static BookDB bInstance;
    public abstract BookDao getBookDao();
    public abstract CdDao getcdDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized BookDB getDBInstance(Context context){
        if(bInstance == null ){
            bInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BookDB.class,
                    DB_Name
            )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return bInstance;
        }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//               BookDao bookDao = bInstance.getBookDao();
//               bookDao.deleteAllBooks();
//
//               Book book = new Book();
//               bookDao.insert(book);
//
//            });
        }
    };
}

