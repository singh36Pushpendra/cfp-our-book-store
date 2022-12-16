package com.store.books.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

// Generate parameterized constructor.
@AllArgsConstructor
public class BookDTO {

    // Used to achieve regular expression validation.
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Invalid either book or author name!")
    public String name, authorName;

    // Check for not null validation.
    @NotNull(message = "Both either book description or book image should not be empty!")
    public String description, image;

    // Define min and max allowed values for book price.
    @Min(value = 100, message = "Book should have price atleast 100 Rs.!")
    @Max(value = 5000, message = "Book can have price atmost 5000 Rs.!")
    public float price;

    // Define min and max allowed values for book quantity.
    @Min(value = 2, message = "Book quantity should be atleast 2 !")
    @Max(value = 20, message = "Book quantity can be atmost 20 !")
    public int quantity;
}
