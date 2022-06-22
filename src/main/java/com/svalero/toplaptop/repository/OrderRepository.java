package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.WorkOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<WorkOrder, Long> {

    List<WorkOrder> findAll();
}
