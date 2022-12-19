package com.store.books.service;

import com.store.books.dto.BookDTO;
import com.store.books.exception.BookStoreException;
import com.store.books.model.Book;
import com.store.books.repository.IBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Mark this class as a service provider.
@Service
public class BookServiceImpl implements IBookService {

    @Autowired // Automatic Dependency Injection.
    IBookRepo bookRepo;

    // Inserts a new book in repository.
    @Override
    public Book insertBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        return bookRepo.save(book);
    }

    // Select a book by id from repository.
    @Override
    public Book selectBook(int id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isPresent())
            return optionalBook.get();
        throw new BookStoreException("Book Id = '" + id + "' not exists in repository!");
    }

    // Update a book by id.
    @Override
    public Book updateBook(int id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isPresent()) {
            Book book = new Book(bookDTO);
            book.setId(id);
            return bookRepo.save(book);
        }
        throw new BookStoreException("Can't Update: Book Id = '" + id + "' not exists in repository!");
    }

    // Delete a book by id.
    @Override
    public String deleteBook(int id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return "Book deleted successfully!";
        }
        throw new BookStoreException("Can't Delete: Book Id = '" + id + "' not exists in repository!");
    }

    // Get all books present in repository.
    @Override
    public List<Book> getAllBook() {
        return bookRepo.findAll();
    }

    // Search a book by name.
    @Override
    public List<Book> searchBook(String name) {
        Optional<List<Book>> optionalBook = bookRepo.findByBookName(name);
        if (optionalBook.isPresent())
            return optionalBook.get();
        throw new BookStoreException("No book exist with name: '" + name + "' in repository!");
    }

    // Overloaded method.
    // Update book quantity by its id.
    @Override
    public Book updateBook(int id, int quantity) {
        // Check quantity value.
        if (quantity < 200 || quantity > 1000)
            throw new BookStoreException("Can't Update: Book quantity should be from 200 to 1000 only!");

        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setQuantity(quantity);
            return bookRepo.save(book);
        }
        throw new BookStoreException("Can't Update: Book Id = '" + id + "' not exists in repository!");
    }

    // Get all books in ascending order by book price.
    @Override
    public List<Book> getAllInAscOrder() {
        return bookRepo.findAll(Sort.by("price"));
    }

    // Get all books in descending order by book price.
    @Override
    public List<Book> getAllInDescOrder() {
        return bookRepo.findAll(Sort.by("price").descending());
    }
}