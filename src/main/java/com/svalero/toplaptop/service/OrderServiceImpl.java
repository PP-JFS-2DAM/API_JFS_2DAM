package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.WorkOrder;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import com.svalero.toplaptop.repository.ComputerRepository;
import com.svalero.toplaptop.repository.OrderRepository;
import com.svalero.toplaptop.repository.TechnicalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private TechnicalRepository technicalRepository;

    public List<WorkOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public WorkOrder findById(long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public WorkOrder addOrder(com.svalero.toplaptop.domain.dto.WorkOrderDTO workOrderDTO) throws ComputerNotFoundException, TechnicalNotFoundException {
        ModelMapper mapper = new ModelMapper();
        WorkOrder workOrder = mapper.map(workOrderDTO, WorkOrder.class);

        workOrder.setComputer(computerRepository.findById(workOrderDTO.getComputer())
                .orElseThrow(ComputerNotFoundException::new));

        workOrder.setTechnical(technicalRepository.findById(workOrderDTO.getTechnical())
                .orElseThrow(TechnicalNotFoundException::new));

        orderRepository.save(workOrder);
        return workOrder;
    }

    public WorkOrder deleteOrder(long id) throws OrderNotFoundException {
        WorkOrder workOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        orderRepository.delete(workOrder);
        return workOrder;
    }

    @Override
    public WorkOrder modifyOrder(long id, com.svalero.toplaptop.domain.dto.WorkOrderDTO workOrderDTO) throws OrderNotFoundException, ComputerNotFoundException, TechnicalNotFoundException {
        orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        WorkOrder workOrder = mapper.map(workOrderDTO, WorkOrder.class);

        workOrder.setId(id);
        workOrder.setComputer(computerRepository.findById(workOrderDTO.getComputer())
                .orElseThrow(ComputerNotFoundException::new));

        workOrder.setTechnical(technicalRepository.findById(workOrderDTO.getTechnical())
                .orElseThrow(TechnicalNotFoundException::new));

        orderRepository.save(workOrder);
        return workOrder;
    }
}
