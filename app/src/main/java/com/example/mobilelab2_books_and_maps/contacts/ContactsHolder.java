package com.example.mobilelab2_books_and_maps.contacts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilelab2_books_and_maps.R;

public class ContactsHolder extends RecyclerView.ViewHolder {
    TextView contactNameSurnameView;
    TextView numberView;

    public ContactsHolder(@NonNull View itemView) {
        super(itemView);
        contactNameSurnameView = itemView.findViewById(R.id.contactNameSurname);
        numberView = itemView.findViewById(R.id.contactNumber);
    }
}