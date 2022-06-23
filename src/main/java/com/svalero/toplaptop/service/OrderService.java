package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.domain.dto.OrderDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    List<Order> findAll();

    List<Order> findAll(String nameSurname, String brandModel) ;

    Order findById(long id) throws OrderNotFoundException;

    Order addOrder(OrderDTO orderDTO) throws ComputerNotFoundException, TechnicalNotFoundException;

    Order deleteOrder(long id) throws OrderNotFoundException;

    Order modifyOrder(long id, OrderDTO orderDTO) throws OrderNotFoundException, ComputerNotFoundException, TechnicalNotFoundException;

}
