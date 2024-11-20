package com.example.mobilelab2_books_and_maps.database;

public class Constants {
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "books";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_AUTHOR = "_author";
    public static final String COLUMN_NAME_TITLE = "_title";
    public static final String COLUMN_NAME_YEAR = "_year";
    public static final String COLUMN_NAME_PAGE = "_page";
    public static final String COLUMN_NAME_ADDRESS = "_address";

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_AUTHOR + " TEXT," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_YEAR + " TEXT," +
                    COLUMN_NAME_PAGE + " TEXT," +
                    COLUMN_NAME_ADDRESS + " TEXT)";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
