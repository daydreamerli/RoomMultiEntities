package com.example.bookmvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmvvm.R;
import com.example.bookmvvm.db.Book;
import com.example.bookmvvm.db.DataConverter;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Book> bookList;
    private Context context;
    private OnItemClickListener bonItemClickListener;

    public void setonItemClickListener(OnItemClickListener bonItemClickListener) {
        this.bonItemClickListener = bonItemClickListener;
    }

    public BookAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context ;
    }

    public void setBookList(List<Book> bookList){
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item,
                parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

                Book book = bookList.get(position);
                myViewHolder.bookImage.setImageBitmap(DataConverter.converByteArray2Image(book.getImage()));
                myViewHolder.titleTextView.setText(book.getBookTitle());
                myViewHolder.authorTextView.setText(book.getBookAuthor());

                myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bonItemClickListener.onItemClick(view,book,position);
                    }
                });
//            }else {
//                myViewHolder.bookImage.setImageBitmap(null);
//                myViewHolder.titleTextView.setText("No Books");
//                myViewHolder.authorTextView.setText("No Information");
//            }

    }
    public void setBook(List<Book> books){
        bookList = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(bookList != null){
            return bookList.size();
        } else
            return 0;
        }

    public interface OnItemClickListener {
        void onItemClick(View view, Book obj, int pos);
    }

}
