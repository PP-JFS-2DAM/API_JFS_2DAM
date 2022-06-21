package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnicalService {

    Flux<Technical> findAll();

    Mono<Technical> findById(long id) throws TechnicalNotFoundException;

    Mono<Technical> addTechnical(Technical technical);

    Mono<Technical> deleteTechnical(long id) throws TechnicalNotFoundException;

    Mono<Technical> modifyTechnical(long id, Technical technical) throws TechnicalNotFoundException;

}
