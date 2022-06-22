package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.domain.dto.OrderDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import com.svalero.toplaptop.repository.ComputerRepository;
import com.svalero.toplaptop.repository.OrderRepository;
import com.svalero.toplaptop.repository.TechnicalRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private TechnicalRepository technicalRepository;

    public Flux<Order> findAll() {
        return (Flux<Order>) orderRepository.findAll();
    }

    @Override
    public Mono<Order> findById(long id) throws OrderNotFoundException {
        return orderRepository.findById(id).onErrorReturn(new Order());
    }

    @Override
    public Mono<Order> addOrder(OrderDTO orderDTO) throws ComputerNotFoundException, TechnicalNotFoundException {

        Mono<Technical> technical = technicalRepository.findById(orderDTO.getTechnical()).onErrorReturn(new Technical());
        Mono<Computer> computer = computerRepository.findById(orderDTO.getComputer()).onErrorReturn(new Computer());
        ModelMapper mapper = new ModelMapper();
        Order order = mapper.map(orderDTO, Order.class);

        order.setComputer(computer.block());

        order.setTechnical(technical.block());

        orderRepository.save(order);
        return orderRepository.save(order);
    }

    public Mono<Order> deleteOrder(long id) throws OrderNotFoundException {
        Mono<Order> order = orderRepository.findById(id).onErrorReturn(new Order());

        orderRepository.delete(order.block());
        return order;
    }

    @Override
    public Mono<Order> modifyOrder(long id, OrderDTO orderDTO) throws OrderNotFoundException, ComputerNotFoundException, TechnicalNotFoundException {
        orderRepository.findById(id).onErrorReturn(new Order());

        Mono<Technical> technical = technicalRepository.findById(orderDTO.getTechnical()).onErrorReturn(new Technical());
        Mono<Computer> computer = computerRepository.findById(orderDTO.getComputer()).onErrorReturn(new Computer());
        Mono<Order> order = orderRepository.findById(id).onErrorReturn(new Order());


        order.block().setComputer(computer.block());
       order.block().setTechnical(technical.block());


        return orderRepository.save(order.block());
    }
}
