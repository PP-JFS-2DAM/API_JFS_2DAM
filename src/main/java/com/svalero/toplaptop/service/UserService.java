package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.User;
import com.svalero.toplaptop.exception.UserNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> findAll();

    Mono<User> findById(long id) throws UserNotFoundException;

    User addUser(User user);

    Mono<User> deleteUser(long id) throws UserNotFoundException;

    Mono<User> modifyUser(long id, User user) throws UserNotFoundException;

}
