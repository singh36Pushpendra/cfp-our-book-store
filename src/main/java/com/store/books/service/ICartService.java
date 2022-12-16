package com.store.books.service;

import com.store.books.dto.CartDTO;
import com.store.books.model.Cart;

import java.util.List;

public interface ICartService {

    Cart insertCart(CartDTO cartDTO);
    Cart selectCart(int id);
    void deleteCart(int id);

    Cart updateCart(int id, CartDTO cartDTO);
    // Overloading.
    Cart updateCart(int id, int quantity);

    List<Cart> selectAllCart();
}
