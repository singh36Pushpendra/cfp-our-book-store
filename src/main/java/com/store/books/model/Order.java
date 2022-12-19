package com.store.books.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "place_order")
@Setter
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date = LocalDate.now();
    private float price;
    private int quantity;
    private String address;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    private boolean cancel;

    public Order(User user, Book book, int quantity, String address, float price, boolean cancel) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.address = address;
        this.price = price;
        this.cancel = cancel;
    }
}