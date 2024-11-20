package com.example.mobilelab2_books_and_maps.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DBManager extends SQLiteOpenHelper {
    public DBManager(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_TABLE);
        onCreate(db);
    }

    public void addStudent(BookPublisher bookPublishers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.COLUMN_NAME_AUTHOR, bookPublishers.getAuthor());
        cv.put(Constants.COLUMN_NAME_TITLE, bookPublishers.getTitle());
        cv.put(Constants.COLUMN_NAME_YEAR, bookPublishers.getYear());
        cv.put(Constants.COLUMN_NAME_PAGE, bookPublishers.getPage());
        cv.put(Constants.COLUMN_NAME_ADDRESS, bookPublishers.getAddress());

        db.insert(Constants.TABLE_NAME, null, cv);
        db.close();
    }

    public void deleteAllStudents() {
        final List<Integer> ids = getAllBooks().stream().map(BookPublisher::getId).collect(Collectors.toList());
        SQLiteDatabase db = this.getWritableDatabase();
        ids.forEach(id -> db.delete(Constants.TABLE_NAME, Constants.COLUMN_NAME_ID + " = ?", new String[]{String.valueOf(id)}));
        db.close();
    }

    public List<BookPublisher> getAllBooks() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<BookPublisher> bookPublishersList = new ArrayList<>();

        String selectAllBooks = "SELECT * FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllBooks, null);

        if (cursor.moveToFirst()) {
            do {
                BookPublisher bookPublisher = new BookPublisher();
                bookPublisher.setId(Integer.parseInt(cursor.getString(0)));
                bookPublisher.setAuthor(cursor.getString(1));
                bookPublisher.setTitle(cursor.getString(2));
                bookPublisher.setYear(cursor.getString(3));
                bookPublisher.setPage(cursor.getString(4));
                bookPublisher.setAddress(cursor.getString(5));
                bookPublishers.add(bookPublisher);
            } while (cursor.moveToNext());
        }
        return bookPublishers;
    }

    public List<BookPublisher> getTitleBooksOlderTenYears() {
        List<BookPublisher> booksOlder10 = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME +
                " WHERE (" + 2024 + " - " + Constants.COLUMN_NAME_YEAR + ") > 10";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookPublisher bookPublisher = new BookPublisher();
                bookPublisher.setId(Integer.parseInt(cursor.getString(0)));
                bookPublisher.setAuthor(cursor.getString(1));
                bookPublisher.setTitle(cursor.getString(2));
                bookPublisher.setYear(cursor.getString(3));
                bookPublisher.setPage(cursor.getString(4));
                bookPublisher.setAddress(cursor.getString(5));
                booksOlder10.add(bookPublisher);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return booksOlder10;
    }

    public double getSelectedPercentage() {
        final double allSize = getAllBooks().size();
        final double older10 = getTitleBooksOlderTenYears().size();
        return older10 / allSize * 100;
    }
}