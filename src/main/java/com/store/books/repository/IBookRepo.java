package com.store.books.repository;

import com.store.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBookRepo extends JpaRepository<Book, Integer> {
    // Define a custom query to find book by name.
    @Query(value = "SELECT * FROM Book where name = :name", nativeQuery = true)
    // Used return type as Optional in order avoid null checking.
    Optional<List<Book>> findByBookName(String name);
}
