package com.store.books.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Used to generate boiler plate code for parameterized constructor and getters.
@AllArgsConstructor
@Getter
public class Email {

    private String to, subject, body;
}
