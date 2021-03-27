package com.example.bookmvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmvvm.R;
import com.example.bookmvvm.db.Cd;
import com.example.bookmvvm.db.DataConverter;

import java.util.List;


public class CdAdapter extends RecyclerView.Adapter<CdViewHolder> {

    private List<Cd> cdList;
    private Context context;
    private OnItemClickListener ConItemClickListener;

    public void setonItemClickListener(OnItemClickListener ConItemClickListener) {
        this.ConItemClickListener = ConItemClickListener;
    }

    public CdAdapter(List<Cd> cdList, Context context) {
        this.cdList = cdList;
        this.context = context ;
    }

    public void setCdList(List<Cd> cdList){
        this.cdList = cdList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_cditem,
                parent,false);

        CdViewHolder cdViewHolder= new CdViewHolder(view);
        return cdViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CdViewHolder cdViewHolder, int position) {

        Cd cd = cdList.get(position);
        cdViewHolder.cdImage.setImageBitmap(DataConverter.converByteArray2Image(cd.getImage()));
        cdViewHolder.albumTextView.setText(cd.getCdAlbumname());
        cdViewHolder.singerTextView.setText(cd.getCdSinger());

        cdViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConItemClickListener.onItemClick(view,cd,position);
            }
        });

    }
    public void setBook(List<Cd> cds){
        cdList = cds;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(cdList != null){
            return cdList.size();
        } else
            return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Cd obj, int pos);
    }

}
