package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.User;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(long id) throws UserNotFoundException {
        return userRepository.findById(id).onErrorReturn(new User());
    }

    @Override
    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> deleteUser(long id) throws UserNotFoundException {
        Mono<User> user = userRepository.findById(id).onErrorReturn(new User());

        userRepository.delete(user.block());
        return user;
    }

    public Mono<User> modifyUser(long id, User newUser) throws UserNotFoundException {
        Mono<User> user = userRepository.findById(id)
                .onErrorReturn(new User());

        userRepository.save(user.block());

        return user;
    }
}
