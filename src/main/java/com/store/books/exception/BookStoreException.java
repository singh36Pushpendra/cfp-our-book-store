package com.store.books.exception;

// Defining a custom exception.
public class BookStoreException extends RuntimeException {
    public BookStoreException(String message) {
        super(message);
    }
}