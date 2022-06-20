package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.domain.User;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.repository.TechnicalRepository;
import com.svalero.toplaptop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {






    @Autowired
    private UsersRepository userRepository;



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User addUser(User user) throws UserNotFoundException {
        return userRepository.save(user);
    }

    public User deleteUser(long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
        return user;
    }

    public User modifyUser(long id, User user) throws UserNotFoundException {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        newUser.setId(id);
        userRepository.save(newUser);
        return newUser;
    }

    public User modifyName(long id, String name) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setName(name);
        return userRepository.save(user);
    }




}
