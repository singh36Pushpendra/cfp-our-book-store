package com.store.books.dto;

public interface ILoginPatterns {

    // Just declaring patterns for email and password.
    String EMAIL_PATTERN = "^[a-zA-Z]+([.+-]?[a-zA-Z0-9]{1,})*[@][a-zA-Z0-9]+[.][a-zA-Z]{2,3}" +
            "([.][a-zA-Z]{2,3})?[,]?$";
    String PASSWORD_PATTERN = "^(?=.{8,})(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]*" +
            "[@#$%^&()+-_][a-zA-Z0-9]*$";
}
