package com.store.books.controller;

import com.store.books.dto.CartDTO;
import com.store.books.dto.ResponseDTO;
import com.store.books.model.Cart;
import com.store.books.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Allows us to define restful web services.
@RestController
// Allows us to map web requests to this handler class.
@RequestMapping("/ourbookstore/cart")
public class CartController {

    @Autowired // Auto DI.
    private ICartService cartService;

    // API to add a new Cart.
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> postCart(@Valid @RequestBody CartDTO cartDTO)
    {
        ResponseDTO responseDTO = new ResponseDTO("Cart saved successfully!", cartService.insertCart(cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get a Cart.
    @GetMapping ("/get/{id}")
    public ResponseEntity<ResponseDTO> getCart(@PathVariable int id)
    {
        ResponseDTO responseDTO = new ResponseDTO("Cart details are as follows: ", cartService.selectCart(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to update a Cart.
    @PutMapping("/put/{id}")
    public ResponseEntity<ResponseDTO> putCart(@PathVariable int id, @Valid @RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Cart updated successfully!", cartService.updateCart(id, cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to delete a Cart.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCart(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Cart deleted successfully!", getCart(id));
        cartService.deleteCart(id);
        return null;
    }

    // API to update Cart quantity.
    @PutMapping("/update/{quantity}")
    public ResponseEntity<ResponseDTO> updateCartQuantity(@RequestParam int id, @PathVariable int quantity) {
        ResponseDTO responseDTO = new ResponseDTO("Cart quantity updated successfully!", cartService.updateCart(id, quantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get all carts.
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllCart() {
        ResponseDTO responseDTO = new ResponseDTO("Details of all cart are as follows: ", cartService.selectAllCart());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}