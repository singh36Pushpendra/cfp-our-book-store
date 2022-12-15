package com.store.books.controller;

import com.store.books.dto.BookDTO;
import com.store.books.dto.ResponseDTO;
import com.store.books.model.Book;
import com.store.books.service.IBookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Allows us to define restful web services, so that it can handle requests made by client.
@RestController
// Allows us to map web requests to this handler class.
@RequestMapping("/ourbookstore/book")
// Enable validation for both request parameters and path variables.
@Validated
public class BookController {

    @Autowired
    IBookService bookService;

    // API to register new book.
    @PostMapping("/post")
    // @Valid -> used to validate constraint applied in BookDTO class.
    public ResponseEntity<ResponseDTO> postBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookService.insertBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book details saved successfully!", book);

        // ResponseEntity is used to configure HTTP response.
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get a book by its id.
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getBook(@PathVariable int id) {
        Book book = bookService.selectBook(id);
        ResponseDTO responseDTO = new ResponseDTO("Book details are as follows: ", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to update a book details by its id.
    @PutMapping("/put/{id}")
    public ResponseEntity<ResponseDTO> putBook(@PathVariable int id, @RequestBody BookDTO bookDTO) {
        Book book = bookService.updateBook(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book details are updated successfully!", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to delete a book details by its id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int id) {
        String deleteMsg = bookService.deleteBook(id);
        ResponseDTO responseDTO = new ResponseDTO("Book details are as follows: ", deleteMsg);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get all book details.
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllBook() {
        List<Book> books = bookService.getAllBook();
        ResponseDTO responseDTO = new ResponseDTO("Book details are as follows: ", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to search book by name.
    @GetMapping("/search/{name}")
    public ResponseEntity<ResponseDTO> searchBookByName(@PathVariable String name) {
        List<Book> books = bookService.searchBook(name);
        ResponseDTO responseDTO = new ResponseDTO("Book details are as follows: ", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to update book quantity.
    @PutMapping("/update/{quantity}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@RequestParam int id, @PathVariable @Min(200) @Max(1000) int quantity) {
        Book book = bookService.updateBook(id, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Book quantity updated successfully!", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get all book details in ascending order.
    @GetMapping("/getall/sortInAsc")
    public ResponseEntity<ResponseDTO> sortInAsc() {
        List<Book> books = bookService.getAllInAscOrder();
        ResponseDTO responseDTO = new ResponseDTO("Book details in ascending order are as follows: ", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get all book details in descending order.
    @GetMapping("/getall/sortInDesc")
    public ResponseEntity<ResponseDTO> sortInDesc() {
        List<Book> books = bookService.getAllInDescOrder();
        ResponseDTO responseDTO = new ResponseDTO("Book details in descending order are as follows: ", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}