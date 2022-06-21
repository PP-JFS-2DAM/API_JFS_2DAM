package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import com.svalero.toplaptop.repository.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TechnicalServiceImpl implements TechnicalService {

    @Autowired
    private TechnicalRepository technicalRepository;

    public Flux<Technical> findAll() {
        return technicalRepository.findAll();
    }

    public Mono<Technical> findById(long id) throws TechnicalNotFoundException {
        return technicalRepository.findById(id).onErrorReturn(new Technical());
    }

    @Override
    public Mono<Technical> addTechnical(Technical technical) {
        return technicalRepository.save(technical);
    }

    public Mono<Technical> deleteTechnical(long id) throws TechnicalNotFoundException {
        Mono<Technical> technical = technicalRepository.findById(id).onErrorReturn(new Technical());

        technicalRepository.delete(technical.block());
        return technical;
    }

    @Override
    public Mono<Technical> modifyTechnical(long id, Technical technical) throws TechnicalNotFoundException {
        technicalRepository.findById(id).onErrorReturn(new Technical());

        technical.setId(id);


        return technicalRepository.save(technical);
    }
}
