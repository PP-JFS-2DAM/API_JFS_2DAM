package com.svalero.toplaptop.controller;

import com.svalero.toplaptop.domain.User;
import com.svalero.toplaptop.exception.ErrorResponse;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(@RequestParam(name = "name", required = false) String name,
                                              @RequestParam(name = "surname", required = false) String surname,
                                              @RequestParam(name = "dni", required = false) String dni,
                                              @RequestParam(name = "all", defaultValue = "false") boolean all) {
        List<User> users;
        logger.info("Inicio findAll users");
        if (all) {
            logger.info("Mostrado de todos los usuarios");
            users = userService.findAll();
        } else {
            logger.info("Filtrado por name, surname, dni");
            users=userService.findAll(name, surname, dni);
        }
        logger.info("Final findAll users");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) throws UserNotFoundException {
        logger.info("Inicio findById users");
        User user = userService.findById(id);
        logger.info("Final findById users");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user)   {
        logger.info("Inicio addUser" + user.getUserImage());
        User newUser = userService.addUser(user);
        logger.info("Final addUser");
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) throws UserNotFoundException {
        logger.info("Inicio deleteUser");
        userService.deleteUser(id);
        logger.info("Final deleteUser");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable long id, @Valid @RequestBody User user)
            throws UserNotFoundException {
        logger.info("Inicio modifyUser");
        User newUser = userService.modifyUser(id, user);
        logger.info("Final modifyUser");
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", unfe.getMessage());
        logger.error(unfe.getMessage(), unfe);
        logger.error(Arrays.toString(unfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("666", "Internal server error");
        logger.error(exception.getMessage(), exception);
        logger.error(Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.error(ex.getMessage(), ex);
        logger.error(Arrays.toString(ex.getStackTrace()));
        return errors;
    }
}
