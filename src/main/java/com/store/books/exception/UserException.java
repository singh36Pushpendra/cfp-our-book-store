package com.store.books.exception;

// Defining a custom exception.
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
