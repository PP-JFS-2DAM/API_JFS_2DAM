package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.WorkOrder;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;

import java.util.List;

public interface OrderService {

    List<WorkOrder> findAll();

    WorkOrder findById(long id) throws OrderNotFoundException;

    WorkOrder addOrder(com.svalero.toplaptop.domain.dto.WorkOrderDTO workOrderDTO) throws ComputerNotFoundException, TechnicalNotFoundException;

    WorkOrder deleteOrder(long id) throws OrderNotFoundException;

    WorkOrder modifyOrder(long id, com.svalero.toplaptop.domain.dto.WorkOrderDTO workOrderDTO) throws OrderNotFoundException, ComputerNotFoundException, TechnicalNotFoundException;

}
