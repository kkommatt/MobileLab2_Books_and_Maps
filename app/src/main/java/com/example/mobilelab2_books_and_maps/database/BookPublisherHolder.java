package com.example.mobilelab2_books_and_maps.database;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilelab2_books_and_maps.R;

public class BookPublisherHolder extends RecyclerView.ViewHolder {
    TextView authorView;
    TextView yearView;
    TextView addressView;
    TextView idView;
    TextView titleView;
    TextView pageView;

    public BookPublisherHolder(@NonNull View itemView) {
        super(itemView);
        authorView = itemView.findViewById(R.id.author);
        titleView = itemView.findViewById(R.id.title);
        yearView = itemView.findViewById(R.id.year);
        pageView = itemView.findViewById(R.id.page);
        addressView = itemView.findViewById(R.id.address);
        idView = itemView.findViewById(R.id.BookId);
    }
}
