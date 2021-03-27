package com.example.bookmvvm.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmvvm.R;
public class CdViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView cdImage;
    TextView albumTextView;
    TextView singerTextView;


    public CdViewHolder(@NonNull final View view) {
        super(view);
        cardView = view.findViewById(R.id.cdcardView);
        cdImage = view.findViewById(R.id.cd_imageview);
        albumTextView = view.findViewById(R.id.album_textview);
        singerTextView = view.findViewById(R.id.singer_textview);


    }

}


