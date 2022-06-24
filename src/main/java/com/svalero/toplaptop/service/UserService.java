package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.User;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> findAll(String name, String surname, String dni);

    User findById(long id) throws UserNotFoundException;

    User addUser(User user);

    void deleteUser(long id) throws UserNotFoundException;

    User modifyUser(long id, User user) throws UserNotFoundException;

}
