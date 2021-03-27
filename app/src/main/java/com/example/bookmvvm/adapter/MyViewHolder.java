package com.example.bookmvvm.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmvvm.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView bookImage;
    TextView titleTextView;
    TextView authorTextView;

    public MyViewHolder(@NonNull final View view) {
        super(view);
        cardView = view.findViewById(R.id.cView);
        bookImage = view.findViewById(R.id.book_imageview);
        titleTextView = view.findViewById(R.id.title_textview);
        authorTextView = view.findViewById(R.id.author_textview);


        }

}
