package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Technical;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface TechnicalRepository extends ReactiveMongoRepository<Technical, Long> {

    Flux<Technical> findAll();
}
