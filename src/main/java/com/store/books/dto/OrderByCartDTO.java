package com.store.books.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

// Generate all args constructor using lombok lib.
@AllArgsConstructor
public class OrderByCartDTO {

    // Validate constraint for +ve number.
    @Positive(message = "Id should not be less than 1!")
    public int cartId;

    // Validate range of order quantity.
    @Min(value = 1, message = "Order quantity should not be less than 1!")
    @Max(value = 10, message = "Order quantity should not be greater than 10!")
    public int quantity;

    // Validate not null constraint.
    @NotNull(message = "Address can't be empty!")
    public String address;

}