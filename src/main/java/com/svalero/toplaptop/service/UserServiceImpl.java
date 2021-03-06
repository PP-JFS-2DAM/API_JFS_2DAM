package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.User;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(String name, String surname, String dni) {
        return userRepository.findByNameContainingOrSurnameContainingOrDniContaining(name, surname, dni);
    }

    public User findById(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteUser(long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

    public User modifyUser(long id, User newUser) throws UserNotFoundException {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        newUser.setId(id);
        userRepository.save(newUser);

        return newUser;
    }
}
