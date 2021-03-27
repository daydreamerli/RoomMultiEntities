package com.example.bookmvvm.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.bookmvvm.db.BookDB;
import com.example.bookmvvm.db.Cd;
import com.example.bookmvvm.db.CdDao;

import java.util.List;

public class CdRespository {

    private BookDB bookDB;
    private CdDao cdDao;
    private LiveData<List<Cd>> mAllCds;
    Context context;
    private static final String TAG = "CdRespository";

    public CdRespository(Context context) {

        bookDB = BookDB.getDBInstance(context);
        cdDao = bookDB.getcdDao();
        mAllCds = cdDao.getAllCds();
    }
    public LiveData<List<Cd>> get_allcds(){
        return mAllCds;
    }


    public void save_cd(Cd cd) {

        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cdDao.insert(cd);
                    Log.d(TAG, "run : saved book => " + cd.getCdAlbumname());
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to save book => " + cd.getCdSinger() +
                            " because: " + e.getMessage() + "");
                }
            }
        });

    }

    public LiveData<Cd> get_cd(int id) {
        return cdDao.get_cd(id);
    }

    public void update_cd(Cd cd) {
        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cdDao.update(cd);
                    Log.d(TAG, "run : update => " + cd.getCdAlbumname() + "information");
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to update book => " + cd.getCdSinger() +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    public void delete_cd(Cd cd) {
        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cdDao.delete(cd);
                    Log.d(TAG, "run : successfully delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete book => " +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }
    public void delete_allcd() {
        BookDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cdDao.deleteAllCds();
                    Log.d(TAG, "run : delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete books => "  +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }
}
