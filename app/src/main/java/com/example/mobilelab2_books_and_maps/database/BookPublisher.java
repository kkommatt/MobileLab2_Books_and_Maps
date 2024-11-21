package com.example.mobilelab2_books_and_maps.database;

public class BookPublisher {
    private int id;
    private String author;
    private String title;
    private String year;
    private String page;
    private String address;

    public String getBookAge() {
        final int age = Integer.parseInt(this.getYear());
        return String.valueOf((2024 - age));
    }

    public BookPublisher() {

    }

    public BookPublisher(String author, String title, String year, String page, String address) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.page = page;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}

