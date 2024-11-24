package com.example.mobilelab2_books_and_maps.database;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import com.example.mobilelab2_books_and_maps.MapsActivity;
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
        final BookPublisher bookPublisher = bookPublishers.get(position);
        holder.authorView.setText(bookPublishers.get(position).getAuthor());
        holder.titleView.setText(bookPublishers.get(position).getTitle());
        holder.yearView.setText(bookPublishers.get(position).getYear());
        holder.pageView.setText(bookPublishers.get(position).getPage());
        holder.addressView.setText(bookPublishers.get(position).getAddress());
        String id = "    " + bookPublishers.get(position).getId();
        holder.idView.setText(id);
        holder.itemView.setOnClickListener(v -> {
            if (bookPublisher != null && bookPublisher.getAddress() != null && !bookPublisher.getAddress().isEmpty()) {
                openMapForBookPublisher(bookPublisher);
            } else {
                Toast.makeText(context, "Address is not defined", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openMapForBookPublisher(BookPublisher bookPublisher) {
        Geocoder geocoder = new Geocoder(context);
        try {
            List<Address> addresses = geocoder.getFromLocationName(bookPublisher.getAddress(), 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address location = addresses.get(0);
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Coordinates for this contact were not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while coordinate search", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return bookPublishers.size();
    }
}
