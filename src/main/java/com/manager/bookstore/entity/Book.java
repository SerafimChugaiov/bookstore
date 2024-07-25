package com.manager.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn")
    private int id;

    @Column(unique = true)
    private String nameBook;

    private int priceBook;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Книга:\n" +
                "isbn = " + id +
                ", \nназвание книги = '" + nameBook + '\'' +
                ", \nцена книги = " + priceBook;
    }
}
