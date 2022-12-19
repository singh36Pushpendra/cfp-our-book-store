package com.store.books.repository;

import com.store.books.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// To mark this interface as a repository.
@Repository
public interface IOrderRepo extends JpaRepository<Order, Integer> {
}
