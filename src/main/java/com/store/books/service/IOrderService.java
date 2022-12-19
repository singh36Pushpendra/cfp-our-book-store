package com.store.books.service;

import com.store.books.dto.OrderByCartDTO;
import com.store.books.dto.OrderDTO;
import com.store.books.model.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService {
    // In interface all methods are abstract.
    // Declare to insert a new order.
    Order insertOrder(OrderDTO orderDTO);

    // Declare to insert a new order by cart.
    Order insertOrder(OrderByCartDTO orderByCartDTO);

    // Declare to get a placed order by id.
    Order selectOrder(int id);

    // Declare to update a placed order.
    Order updateOrder(int id, OrderDTO orderDTO);

    // Declare to delete order by id.
    void deleteOrder(int id);

    // Declare to get all order information.
    List<Order> getAllOrder();
}