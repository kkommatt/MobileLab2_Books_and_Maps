package com.example.mobilelab2_books_and_maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilelab2_books_and_maps.database.BookPublisher;
import com.example.mobilelab2_books_and_maps.database.BookPublisherAdapter;
import com.example.mobilelab2_books_and_maps.database.DBManager;

import java.util.List;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Reference to your layout file

        RecyclerView recyclerView = findViewById(R.id.recyclerViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try (DBManager dbManager = new DBManager(this)) {
            dbManager.deleteAllBooks();
            dbManager.addBook(new BookPublisher("Омельчук", "ООП", "2005", "72", "проспект Академіка Глушкова 4д"));
            dbManager.addBook(new BookPublisher("Шишацька", "ТП", "2020", "65", "Володимирська 33"));
            dbManager.addBook(new BookPublisher("Зінько", "ТПР", "2024", "67", "Володимирська 40"));
            dbManager.addBook(new BookPublisher("Тарануха", "ІС", "2015", "70", "проспект Академіка Глушкова 4а"));
            dbManager.addBook(new BookPublisher("Ткаченко", "РПМП", "2009", "55", "проспект Академіка Глушкова 4г"));

            List<BookPublisher> bookPublishers = dbManager.getAllBooks();
            BookPublisherAdapter bookPublishersAdapter = new BookPublisherAdapter(this, bookPublishers);
            recyclerView.setAdapter(bookPublishersAdapter);

            Button buttonAge = findViewById(R.id.buttonAge);
            Button buttonOlder = findViewById(R.id.button2);
            Button buttonShowAll = findViewById(R.id.button3);
            Button btnReturn = findViewById(R.id.btnReturn);
            TextView textViewPercentage = findViewById(R.id.booksPercentage);

            buttonAge.setOnClickListener(v -> {
                List<BookPublisher> booksOlder10 = dbManager.getTitleBooksOlderTenYears();
                BookPublisherAdapter booksOlder10Adapter = new BookPublisherAdapter(this, booksOlder10);
                recyclerView.setAdapter(booksOlder10Adapter);
            });

            buttonShowAll.setOnClickListener(v -> {
                List<BookPublisher> booksAll = dbManager.getAllBooks();
                BookPublisherAdapter booksAllAdapter = new BookPublisherAdapter(this, booksAll);
                recyclerView.setAdapter(booksAllAdapter);
            });

            buttonOlder.setOnClickListener(v -> {
                double selectedPercentage = dbManager.getSelectedPercentage();
                textViewPercentage.setText(String.format(Locale.getDefault(), "%.2f", selectedPercentage));
            });

            btnReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}