package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.Computer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends CrudRepository<Computer, Long> {

    List<Computer> findAll();
}
