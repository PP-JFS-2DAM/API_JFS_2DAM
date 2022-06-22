package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.Computer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ComputerRepository extends ReactiveMongoRepository<Computer, Long> {

    Flux<Computer> findAll();
    Flux<Computer> findByBrand(String brand);

}


