package com.example.bookmvvm.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CdTable")
public class Cd {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String cdAlbumname;
    private String cdSinger;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;

    public int getId() {
        return id;
    }

    public String getCdAlbumname() {
        return cdAlbumname;
    }

    public String getCdSinger() {
        return cdSinger;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setCdAlbumname(String cdAlbumname) {
        this.cdAlbumname = cdAlbumname;
    }

    public void setCdSinger(String cdSinger) {
        this.cdSinger = cdSinger;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cd{" +
                "id=" + id +
                ", Album='" + cdAlbumname + '\'' +
                ", Singer='" + cdSinger + '\'' +
                '}';
    }
}
