package com.store.books.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

// Generate parameterized constructor.
@AllArgsConstructor
public class LoginDTO implements ILoginPatterns {
    @Email(regexp = EMAIL_PATTERN, message = "Invalid email value!")
    public String email;

    @Pattern(regexp = PASSWORD_PATTERN, message = "Invalid password value!")
    public String password;
}
