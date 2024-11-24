package com.example.mobilelab2_books_and_maps.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.mobilelab2_books_and_maps.R;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsHolder> {

    Context context;
    List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsHolder(LayoutInflater.from(context).inflate(R.layout.contact_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
        final Contact contact = contacts.get(position);
        holder.numberView.setText(contact == null ? "" : contact.getNumber());
        holder.contactNameSurnameView.setText(contact == null ? "" : contact.getName() + " " + contact.getSurname());
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
