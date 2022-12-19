package com.store.books.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderByCartDTO {

    //
    @Positive(message = "Id should not be less than 1!")
    public int cartId;

    // Defining range of order quantity.
    @Min(value = 1, message = "Order quantity should not be less than 1!")
    @Max(value = 5, message = "Order quantity should not be greater than 5!")
    public int quantity;

    @NotNull(message = "Address can't be empty!")
    public String address;

    @NotNull(message = "Cancel value can't be empty!")
    public boolean cancel;
}
