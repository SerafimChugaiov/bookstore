package com.manager.bookstore.dto;

import jakarta.persistence.Column;

public class BookDto {
    @Column(nullable = false)
    private String nameBook;

    @Column(nullable = false)
    private int priceBook;

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(int priceBook) {
        this.priceBook = priceBook;
    }
}
