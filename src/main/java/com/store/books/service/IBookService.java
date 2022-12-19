package com.store.books.service;

import com.store.books.dto.BookDTO;
import com.store.books.model.Book;

import java.util.List;

public interface IBookService {

    // Abstract method to insert new book in repository.
    Book insertBook(BookDTO bookDTO);

    // Abstract method to get a book.
    Book selectBook(int id);

    // Abstract method to update a book by its id.
    Book updateBook(int id, BookDTO bookDTO);

    // Abstract method to delete a book by its id.
    String deleteBook(int id);

    // Abstract method to get all books.
    List<Book> getAllBook();

    // Abstract method to search book by name.
    List<Book> searchBook(String name);

    // Abstract method to update book quantity.
    Book updateBook(int id, int quantity);

    // Abstract method to get all book in ascending order.
    List<Book> getAllInAscOrder();

    // Abstract method to get all book in descending order.
    List<Book> getAllInDescOrder();

}