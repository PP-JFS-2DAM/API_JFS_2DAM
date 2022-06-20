package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {




    List<Order> findAll();

    Order findById(long id) throws OrderNotFoundException;

    Order addOrder(OrderDTO orderDTO) throws OrderNotFoundException;

    Order deleteOrder(long id) throws OrderNotFoundException;

    Order modifyOrder(long id, OrderDTO orderDTO) throws OrderNotFoundException, ComputerNotFoundException;

    Order modifyDescription(long id, String description) throws OrderNotFoundException;






}
