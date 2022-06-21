package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.domain.dto.OrderDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import com.svalero.toplaptop.repository.ComputerRepository;
import com.svalero.toplaptop.repository.OrderRepository;
import com.svalero.toplaptop.repository.TechnicalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order addOrder(OrderDTO orderDTO) throws ComputerNotFoundException, TechnicalNotFoundException {
        Mono<Computer> computer = computerRepository.findById(orderDTO.getComputer()).onErrorReturn(new Computer());
        ModelMapper mapper = new ModelMapper();
        Order order = mapper.map(orderDTO, Order.class);

        order.setComputer(computer.block());

        order.setTechnical(technicalRepository.findById(orderDTO.getTechnical())
                .orElseThrow(TechnicalNotFoundException::new));

        orderRepository.save(order);
        return order;
    }

    public Order deleteOrder(long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        orderRepository.delete(order);
        return order;
    }

    @Override
    public Order modifyOrder(long id, OrderDTO orderDTO) throws OrderNotFoundException, ComputerNotFoundException, TechnicalNotFoundException {
        orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        Mono<Computer> computer = computerRepository.findById(orderDTO.getComputer()).onErrorReturn(new Computer());
        ModelMapper mapper = new ModelMapper();
        Order order = mapper.map(orderDTO, Order.class);

        order.setId(id);
        order.setComputer(computer.block());
        order.setTechnical(technicalRepository.findById(orderDTO.getTechnical())
                .orElseThrow(TechnicalNotFoundException::new));

        orderRepository.save(order);
        return order;
    }
}
