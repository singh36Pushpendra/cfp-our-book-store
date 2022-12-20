package com.store.books.controller;

import com.store.books.dto.OrderByCartDTO;
import com.store.books.dto.OrderDTO;
import com.store.books.dto.ResponseDTO;
import com.store.books.model.Email;
import com.store.books.model.Order;
import com.store.books.service.IEmailService;
import com.store.books.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Mark a class as a request handler.
// Used to create restful web services.
@RestController
// Used to map web requests onto this handler class.
@RequestMapping("/ourbookstore/order")
public class OrderController {
    // Declare instance reference variable.
    private ResponseDTO responseDTO;
    private Email email;
    private Order order;

    @Autowired
    private IOrderService orderService;

    @Autowired
    IEmailService emailService;

    // API to place order.
    // Used to map HTTP post request onto this handler method.
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> postOrder(@Valid @RequestBody OrderDTO orderDTO) {
        order = orderService.insertOrder(orderDTO);
        responseDTO = new ResponseDTO("Order placed successfully!", order);
        // Use JMS.
        email = new Email(order.getUser().getEmail(), "Ordered New from OurBookStore!", "Order of book " + order.getBook().getName() + " placed successfully!");
        emailService.sendMail(email);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to place order from cart.
    @PostMapping("/postbycart")
    public ResponseEntity<ResponseDTO> postOrder(@Valid @RequestBody OrderByCartDTO orderByCartDTO) {
        order = orderService.insertOrder(orderByCartDTO);
        responseDTO = new ResponseDTO("Order placed successfully!", order);
        email = new Email(order.getUser().getEmail(), "Ordered New from OurBookStore!", "Order of book " + order.getBook().getName() + " placed successfully!");
        emailService.sendMail(email);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get placed order by id.
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getOrder(@PathVariable int id) {
        responseDTO = new ResponseDTO("Order details are as follows: ", orderService.selectOrder(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to update placed order by id.
    @PutMapping("/put/{id}")
    public ResponseEntity<ResponseDTO> putOrder(@PathVariable int id, @Valid @RequestBody OrderDTO orderDTO) {
        responseDTO = new ResponseDTO("Order updated successfully!", orderService.updateOrder(id, orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to delete placed order by id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Order deleted successfully!", HttpStatus.OK);
    }

    // API to cancel order by id.
    @PutMapping("/cancel/{id}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int id) {
        order = orderService.cancelOrder(id);
        email = new Email(order.getUser().getEmail(), "Canclled order from OurBookStore!", "Order of book " + order.getBook().getName() + " cancelled successfully!");
        emailService.sendMail(email);
        responseDTO = new ResponseDTO("Order cancelled successfully!", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get all orders.
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllOrder() {
        responseDTO = new ResponseDTO("All orders are as follows: ", orderService.getAllOrder());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}