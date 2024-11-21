package com.example.mobilelab2_books_and_maps.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.mobilelab2_books_and_maps.R;

public class BookPublisherAdapter extends RecyclerView.Adapter<BookPublisherHolder> {
    Context context;
    List<BookPublisher> bookPublishers;

    public BookPublisherAdapter(Context context, List<BookPublisher> bookPublishers) {
        this.context = context;
        this.bookPublishers = bookPublishers;
    }

    @NonNull
    @Override
    public BookPublisherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookPublisherHolder(LayoutInflater.from(context).inflate(R.layout.book_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookPublisherHolder holder, int position) {
        holder.authorView.setText(bookPublishers.get(position).getAuthor());
        holder.titleView.setText(bookPublishers.get(position).getTitle());
        holder.yearView.setText(bookPublishers.get(position).getYear());
        holder.pageView.setText(bookPublishers.get(position).getPage());
        holder.addressView.setText(bookPublishers.get(position).getAddress());
        String id = "    " + bookPublishers.get(position).getId();
        holder.idView.setText(id);
    }

    @Override
    public int getItemCount() {
        return bookPublishers.size();
    }
}
