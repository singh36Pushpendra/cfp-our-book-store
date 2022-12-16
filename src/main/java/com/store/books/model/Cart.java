package com.store.books.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Generate boiler plate code for zeroth argument constructor and setters, getters.
@NoArgsConstructor
@Setter
@Getter
// Specify this class as an entity and is mapped to a database table.
@Entity
public class Cart {

    @Id // To make 'id' as primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Declare 'id' as auto-increment column.
    private int id;

    // Used to map Cart entity with User.
    @OneToOne
    private User user;

    // Allows to define many to one relationship between Cart and Book entities.
    @ManyToOne
    private Book book;
    private int quantity;

    public Cart(User user, Book book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }
}