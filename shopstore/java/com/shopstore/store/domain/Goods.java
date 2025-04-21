package com.shopstore.store.domain; //實體類

import java.util.List;

public class Goods
{
    private long id;
    private String bookName;
    private double price;
    private String author;
    private String translator;
    private String publisher;
    private Long isbn;
    private String language;
    private String image;
    private int bookTypeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(int bookTypeId) {
        this.bookTypeId = bookTypeId;
    }
}
