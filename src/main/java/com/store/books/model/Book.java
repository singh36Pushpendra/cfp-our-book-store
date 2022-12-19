package com.store.books.model;

import com.store.books.dto.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Used lombok library to generate parameterized constructor and setters, getters for all instance variables.
@AllArgsConstructor
@Setter
@Getter
// Marked this class to map into database table.
@Entity
// Generate zeroth args constructor.
@NoArgsConstructor
public class Book {

    // Define 'id' variable as primary key.
    @Id
    // To automatic increment 'id' value.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name, authorName, description, image;
    private float price;
    private int quantity;

    // Parameterized constructor to transfer 'BookDTO' class object data to 'Book' class object.
    public Book(BookDTO bookDTO) {
        name = bookDTO.name;
        authorName = bookDTO.authorName;
        description = bookDTO.description;
        image = bookDTO.image;
        price = bookDTO.price;
        quantity = bookDTO.quantity;
    }

}