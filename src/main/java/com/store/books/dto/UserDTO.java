package com.store.books.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

// Generating boiler plate code for parameterized constuctor by taking all instance variables.
@AllArgsConstructor
// Using inheritance.
public class UserDTO implements ILoginPatterns {

    // To validate email.
    @Email(regexp = EMAIL_PATTERN, message = "Invalid email value!")
    public String email;

    // To validate password.
    @Pattern(regexp = PASSWORD_PATTERN, message = "Invalid password value!")
    public String password;

    // To validate first and last name.
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Invalid first or last name!")
    public String firstName, lastName;

    // Applying not null constraint on address.
    @NotNull(message = "Address should not be empty!")
    public String address;

    // Applying constraint for date of birth.
    @NotNull(message = "Date of birth cannot be empty!")
    @Past(message = "Date of birth should be of past!")
    public LocalDate dob;

}