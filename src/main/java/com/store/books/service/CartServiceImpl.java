package com.store.books.service;

import com.store.books.dto.CartDTO;
import com.store.books.exception.BookStoreException;
import com.store.books.model.Book;
import com.store.books.model.Cart;
import com.store.books.model.User;
import com.store.books.repository.ICartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Mark a class as a service provider.
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepo cartRepo;

    @Autowired // Automatic dependency injection.
    private IUserService userService;

    @Autowired
    private IBookService bookService;

    // Get Cart Object from CartDTO.
    private Cart getCartObject(CartDTO cartDTO) {
        User user = userService.getUser(cartDTO.userId);
        Book book = bookService.selectBook(cartDTO.bookId);
        int bookQuantity = book.getQuantity();
        int cartQuantity = cartDTO.quantity;
        if (cartQuantity < bookQuantity)
            return new Cart(user, book, cartQuantity);
        throw new BookStoreException("No sufficient cart found: Only '" + bookQuantity + "' available!");
    }

    // Insert a new cart to repository.
    @Override
    public Cart insertCart(CartDTO cartDTO) {
        Cart cart = getCartObject(cartDTO);
        return cartRepo.save(cart);
    }

    // Select a cart by id from repository.
    @Override
    public Cart selectCart(int id) {
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if (optionalCart.isPresent())
            return optionalCart.get();
        throw new BookStoreException("Cart Id: '" + id + "' does'nt exist in repository!");
    }

    // Delete a cart by id from repository.
    @Override
    public void deleteCart(int id) {
        if (cartRepo.existsById(id))
            cartRepo.deleteById(id);
        throw new BookStoreException("Can't Delete: Cart Id: '" + id + "' does'nt exist in repository!");
    }

    // Update a cart by id.
    @Override
    public Cart updateCart(int id, CartDTO cartDTO) {
        if (cartRepo.existsById(id)){
            Cart cart = getCartObject(cartDTO);
            cart.setId(id);
            return cartRepo.save(cart);
        }
        throw new BookStoreException("Can't Update: Cart Id: '" + id + "' does'nt exist in repository!");
    }

    // Update a cart quantity by id.
    @Override
    public Cart updateCart(int id, int quantity) {
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Book book = cart.getBook();
            if (quantity < book.getQuantity()) {
                cart.setQuantity(quantity);
                return cartRepo.save(cart);
            }
            throw new BookStoreException("No sufficient cart found: Only '" + book.getQuantity() + "' available!");
        }
        throw new BookStoreException("Can't Update: Cart Id: '" + id + "' does'nt exist in repository!");
    }

    // Get all cart from repository.
    @Override
    public List<Cart> selectAllCart() {
        return cartRepo.findAll();
    }
}
