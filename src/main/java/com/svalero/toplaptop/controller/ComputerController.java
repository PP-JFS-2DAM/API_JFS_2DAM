package com.svalero.toplaptop.controller;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.dto.ComputerDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.ErrorResponse;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.service.ComputerService;
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
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    private final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @GetMapping("/computers")
    public ResponseEntity<List<Computer>> findAll() {
        logger.info("Inicio findAll computers");
        List<Computer> centers = computerService.findAll();
        logger.info("Final findAll computers");
        return new ResponseEntity<>(centers, HttpStatus.OK);
    }

    @GetMapping("/computer/{id}")
    public ResponseEntity<Computer> findById(@PathVariable long id) throws ComputerNotFoundException {
        logger.info("Inicio findById computers");
        Computer computer = computerService.findById(id);
        logger.info("Final findById computers");
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }

    @PostMapping("/computer")
    public ResponseEntity<Computer> addComputer(@Valid @RequestBody ComputerDTO computerDTO) throws UserNotFoundException {
        logger.info("Inicio addComputer");
        Computer computer = computerService.addComputer(computerDTO);
        logger.info("Final addComputer");
        return new ResponseEntity<>(computer, HttpStatus.CREATED);
    }

    @DeleteMapping("/computer/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable long id) throws ComputerNotFoundException {
        logger.info("Inicio deleteComputer");
        computerService.deleteComputer(id);
        logger.info("Final deleteComputer");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/computer/{id}")
    public ResponseEntity<Computer> modifyComputer(@PathVariable long id, @Valid @RequestBody ComputerDTO computerDTO)
            throws ComputerNotFoundException, UserNotFoundException {
        logger.info("Inicio modifyComputer");
        Computer computer = computerService.modifyComputer(id, computerDTO);
        logger.info("Final modifyComputer");
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }

    @ExceptionHandler(ComputerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleComputerNotFoundException(ComputerNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        logger.error(Arrays.toString(cnfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        logger.error(Arrays.toString(cnfe.getStackTrace()));
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
