package com.store.books.service;

import com.store.books.dto.OrderByCartDTO;
import com.store.books.dto.OrderDTO;
import com.store.books.exception.BookStoreException;
import com.store.books.model.Book;
import com.store.books.model.Cart;
import com.store.books.model.Order;
import com.store.books.model.User;
import com.store.books.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Mark this class as a service provider.
@Service
public class OrderServiceImpl implements IOrderService {
    // Declare instance reference variable.
    private User user;
    private Book book;
    private Order order;

    @Autowired // Automatic dependency injection.
    private IUserService userService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IOrderRepo orderRepo;

    // Get Order object from OrderDTO.
    private Order getOrderObject(OrderDTO orderDTO) {
        book = bookService.selectBook(orderDTO.bookId);
        int quantity = orderDTO.quantity;

        // Compare order quantity with book quantity.
        if (quantity < book.getQuantity()) {
            user = userService.getUser(orderDTO.userId);
            String address = orderDTO.address;

            float totalPrice = book.getPrice() * quantity;
            return new Order(user, book, quantity, address, totalPrice);
        }
        throw new BookStoreException("Can't Place Order: No sufficient book quantity available!");
    }

    // Inserts a new order.
    @Override
    public Order insertOrder(OrderDTO orderDTO) {
        order = getOrderObject(orderDTO);
        return orderRepo.save(order);
    }

    // Inserts a new order by cart.
    @Override
    public Order insertOrder(OrderByCartDTO orderByCartDTO) {
        Cart cart = cartService.selectCart(orderByCartDTO.cartId);
        OrderDTO orderDTO = new OrderDTO(orderByCartDTO.quantity, orderByCartDTO.address,
                cart.getUser().getId(), cart.getBook().getId());

        return insertOrder(orderDTO);
    }

    // Selects an order by id.
    @Override
    public Order selectOrder(int id) {
        return orderRepo.findById(id).orElseThrow(() ->
                        new BookStoreException("Order Id: '" + id + "' does'nt exists in repository!"));
    }

    // Updates an order by id.
    @Override
    public Order updateOrder(int id, OrderDTO orderDTO) {
        selectOrder(id);
        order = getOrderObject(orderDTO);
        order.setId(id);
        return orderRepo.save(order);
    }

    // Deletes an order by id.
    @Override
    public void deleteOrder(int id) {
        orderRepo.delete(selectOrder(id));
    }

    @Override
    public Order cancelOrder(int id) {
        order = selectOrder(id);
        order.setCancel(true);
        return orderRepo.save(order);
    }

    // Get all order from repository.
    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = orderRepo.findAll();
        if (orders.isEmpty())
            throw new BookStoreException("No orders exists in repository!");
        return orders;
    }
}