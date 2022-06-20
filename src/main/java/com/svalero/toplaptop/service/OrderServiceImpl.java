package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;

import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.repository.ComputerRepository;
import com.svalero.toplaptop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements ComputerService {



    @Autowired
    private OrderRepository orderRepository;



    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order addOrder(Order order) throws OrderNotFoundException {
        return orderRepository.save(order);
    }

    public Order deleteOrder(long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        orderRepository.delete(order);
        return order;
    }

    public Order modifyOrder(long id, Order order) throws OrderNotFoundException {
        orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        newOrder.setId(id);
        orderRepository.save(newOrder);
        return newOrder;
    }

    public Order modifyDescription(long id, String description) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
        order.setDescription(description);
        return orderRepository.save(order);
    }




}
