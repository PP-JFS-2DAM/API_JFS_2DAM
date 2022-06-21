package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.dto.ComputerDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.UserNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ComputerService {

    Flux<Computer> findAll();

    Mono<Computer> findById(long id) throws ComputerNotFoundException;

    Mono<Computer> addComputer(ComputerDTO computerDTO) throws UserNotFoundException;

    Mono<Computer> deleteComputer(long id) throws ComputerNotFoundException;

    Mono<Computer> modifyComputer(long id, ComputerDTO computerDTO) throws ComputerNotFoundException, UserNotFoundException;

}
