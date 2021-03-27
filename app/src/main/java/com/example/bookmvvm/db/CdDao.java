package com.example.bookmvvm.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CdDao {
    @Query("SELECT * FROM CdTable")
    LiveData<List<Cd>> getAllCds();

    @Query("SELECT * FROM CdTable WHERE id = :id")
    LiveData<Cd> get_cd(int id);

    @Insert
    void insert(Cd cds);

    @Update
    void update(Cd cd);

    @Delete
    void delete(Cd cd);

    @Query("DELETE FROM CdTable")
    void deleteAllCds();
}
