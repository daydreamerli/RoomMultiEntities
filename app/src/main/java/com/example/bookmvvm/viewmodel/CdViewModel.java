package com.example.bookmvvm.viewmodel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookmvvm.db.Cd;


import java.util.List;

public class CdViewModel extends AndroidViewModel {


    private CdRespository cdRespository;
    private final LiveData<List<Cd>>  currentCds;

    public CdViewModel(@NonNull Application application) {
        super(application);
        cdRespository = new CdRespository(application);
        currentCds = cdRespository.get_allcds();
    }
    public LiveData<List<Cd>> getCurrentCds(){
        return currentCds;
    }


    public void save_book(Cd cd){

        cdRespository.save_cd(cd);
    }
    public void delete_cd(Cd cd){

        cdRespository.delete_cd(cd);
    }
    public void delete_all(){

        cdRespository.delete_allcd();
    }
}