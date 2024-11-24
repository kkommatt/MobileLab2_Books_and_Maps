package com.example.mobilelab2_books_and_maps.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilelab2_books_and_maps.MainActivity;
import com.example.mobilelab2_books_and_maps.R;

public class NotificationsActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS_PERMISSION);
        } else {
            List<Contact> koContacts = getContactsBySuffix("ko");

            RecyclerView recyclerView = findViewById(R.id.recyclerViewContact);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ContactsAdapter contactsAdapter = new ContactsAdapter(this, koContacts);
            recyclerView.setAdapter(contactsAdapter);
        }
    }

    public List<Contact> getContactsBySuffix(String suffix) {
        List<Contact> contacts = new ArrayList<>();

        String[] phoneProjection = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        String selection = "UPPER(" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + ") LIKE ?";
        String[] selectionArgs = {"%" + suffix.toUpperCase()};

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                phoneProjection,
                selection,
                selectionArgs,
                null
        );

        if (cursor != null) {
            try {
                int contactIdIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
                int displayNameIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

                while (cursor.moveToNext()) {
                    String contactId = cursor.getString(contactIdIndex);
                    String contactName = cursor.getString(displayNameIndex);
                    String contactNumber = cursor.getString(phoneNumberIndex);

                    String contactAddress = getContactAddress(contactId);

                    contacts.add(new Contact(contactName, " ", contactNumber, contactAddress));
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }

        return contacts;
    }

    private String getContactAddress(String contactId) {
        String address = null;
        String[] addressProjection = {
                ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS
        };

        String addressSelection = ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?";
        String[] addressSelectionArgs = {contactId};

        Cursor addressCursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                addressProjection,
                addressSelection,
                addressSelectionArgs,
                null
        );

        if (addressCursor != null) {
            try {
                if (addressCursor.moveToFirst()) {
                    int addressIndex = addressCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS);
                    address = addressCursor.getString(addressIndex);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                addressCursor.close();
            }
        }

        return address;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_CONTACTS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            }
        }
    }
}