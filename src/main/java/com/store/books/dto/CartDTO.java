package com.store.books.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

// Generate parameterized constructor with all instance variables.
@AllArgsConstructor
public class CartDTO {

    // Apply constraint for int variable's value should be greater or equal to '1'.
    @Min(1)
    public int userId, bookId, quantity;
}