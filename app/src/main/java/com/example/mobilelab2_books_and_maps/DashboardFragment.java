package com.example.mobilelab2_books_and_maps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilelab2_books_and_maps.database.BookPublisher;
import com.example.mobilelab2_books_and_maps.database.BookPublisherAdapter;
import com.example.mobilelab2_books_and_maps.database.DBManager;

import java.util.List;
import java.util.Locale;


public class DashboardFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        try (DBManager dbManager = new DBManager(requireContext())) {
            dbManager.deleteAllBooks();
            dbManager.addBook(new BookPublisher("Омельчук", "ООП", "2005", "72", "проспект Академіка Глушкова 4д"));
            dbManager.addBook(new BookPublisher("Шишацька", "ТП", "2010", "65", "Володимирська 33"));
            dbManager.addBook(new BookPublisher("Зінько", "ТПР", "2004", "67", "Володимирська 40"));
            dbManager.addBook(new BookPublisher("Тарануха", "ІС", "2008", "70", "проспект Академіка Глушкова 4а"));
            dbManager.addBook(new BookPublisher("Ткаченко", "РПМП", "2009", "55", "проспект Академіка Глушкова 4г"));
            List<BookPublisher> bookPublishers = dbManager.getAllBooks();
            BookPublisherAdapter bookPublishersAdapter = new BookPublisherAdapter(requireContext(), bookPublishers);
            recyclerView.setAdapter(bookPublishersAdapter);
            Button buttonAge = view.findViewById(R.id.buttonAge);
            Button buttonOlder = view.findViewById(R.id.button2);
            Button buttonShowAll = view.findViewById(R.id.button3);
            TextView textViewPercentage = view.findViewById(R.id.booksPercentage);
            buttonAge.setOnClickListener(v -> {
                RecyclerView recyclerView1 = view.findViewById(R.id.recyclerViewBooks);
                recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));
                List<BookPublisher> booksOlder10 = dbManager.getTitleBooksOlderTenYears();
                BookPublisherAdapter booksOlder10Adapter = new BookPublisherAdapter(requireContext(), booksOlder10);
                recyclerView1.setAdapter(booksOlder10Adapter);
            });
            buttonShowAll.setOnClickListener(v -> {
                RecyclerView recyclerView12 = view.findViewById(R.id.recyclerViewBooks);
                recyclerView12.setLayoutManager(new LinearLayoutManager(requireContext()));
                List<BookPublisher> booksAll = dbManager.getAllBooks();
                BookPublisherAdapter booksAllAdapter = new BookPublisherAdapter(requireContext(), booksAll);
                recyclerView12.setAdapter(booksAllAdapter);
            });

            buttonOlder.setOnClickListener(v -> {
                double selectedPercentage = dbManager.getSelectedPercentage();
                textViewPercentage.setText(String.format(Locale.getDefault(), "%.2f", selectedPercentage));
            });

            return view;
        }
    }
}