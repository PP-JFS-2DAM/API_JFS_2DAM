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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public ResponseEntity<Flux<User>> findAll() {
        logger.info("Inicio findAll users");
        Flux<User> users = userService.findAll();
        logger.info("Final findAll users");
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Mono<User>> findById(@PathVariable long id) throws UserNotFoundException {
        logger.info("Inicio findById users");
        Mono<User> user = userService.findById(id);
        logger.info("Final findById users");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user)   {
        logger.info("Inicio addUser");
        Mono<User> newUser = userService.addUser(user);
        logger.info("Final addUser");
        return ResponseEntity.ok(newUser.block());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Mono<User>> deleteUser(@PathVariable long id) throws UserNotFoundException {
        logger.info("Inicio deleteUser");
        Mono<User> user = userService.deleteUser(id);
        logger.info("Final deleteUser");
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Mono<User>> modifyUser(@PathVariable long id, @Valid @RequestBody User user)
            throws UserNotFoundException {
        logger.info("Inicio modifyUser");
        Mono<User> newUser = userService.modifyUser(id, user);
        logger.info("Final modifyUser");
        return ResponseEntity.ok(newUser);
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
