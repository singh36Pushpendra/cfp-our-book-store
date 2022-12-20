package com.store.books.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// Define this class can be mapped to a table.
@Entity
// Define name for database table.
@Table(name = "place_order")
// Generate boiler plate for setters, getters for all instance variables and a zeroth args constructor.
@Setter
@Getter
@NoArgsConstructor
public class Order {
    @Id // Define 'id' variable as primary key for table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define 'id' as auto incremented field.
    private int id;

    private LocalDate date = LocalDate.now();
    private float price;
    private int quantity;
    private String address;

    // Define many to one relationship between Order and User entity class.
    // Multiple Order can be made by same user.
    @ManyToOne
    private User user;

    // Define many to one relationship between Order and Book entity class.
    // Multiple Order can be made for same book.
    @ManyToOne
    private Book book;

    private boolean cancel;

    // Parameterized constructor.
    public Order(User user, Book book, int quantity, String address, float price) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.address = address;
        this.price = price;
    }
}