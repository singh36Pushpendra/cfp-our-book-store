package com.store.books.repository;

import com.store.books.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepo extends JpaRepository<Cart, Integer> {
}
