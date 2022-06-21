package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.domain.dto.OrderDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {

    Flux<Order> findAll();

    Mono<Order> findById(long id) throws OrderNotFoundException;

    Mono<Order> addOrder(OrderDTO orderDTO) throws ComputerNotFoundException, TechnicalNotFoundException;

    Mono<Order> deleteOrder(long id) throws OrderNotFoundException;

    Mono<Order> modifyOrder(long id, OrderDTO orderDTO) throws OrderNotFoundException, ComputerNotFoundException, TechnicalNotFoundException;

}
