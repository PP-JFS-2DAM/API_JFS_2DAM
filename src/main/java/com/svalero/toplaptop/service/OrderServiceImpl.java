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

import java.time.LocalDate;
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
    public List<Order> findAll(String nameSurname, String brandModel) {
        return orderRepository.findByComputer_BrandContainingOrComputer_ModelContainingOrComputer_User_NameContainingOrComputer_User_SurnameContaining(nameSurname, brandModel);
    }

    @Override
    public Order addOrder(OrderDTO orderDTO) throws ComputerNotFoundException, TechnicalNotFoundException {
        ModelMapper mapper = new ModelMapper();
        Order order = mapper.map(orderDTO, Order.class);

        order.setComputer(computerRepository.findById(orderDTO.getComputer())
                .orElseThrow(ComputerNotFoundException::new));

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

        ModelMapper mapper = new ModelMapper();
        Order order = mapper.map(orderDTO, Order.class);

        order.setId(id);
        order.setComputer(computerRepository.findById(orderDTO.getComputer())
                .orElseThrow(ComputerNotFoundException::new));

        order.setTechnical(technicalRepository.findById(orderDTO.getTechnical())
                .orElseThrow(TechnicalNotFoundException::new));

        orderRepository.save(order);
        return order;
    }
}
