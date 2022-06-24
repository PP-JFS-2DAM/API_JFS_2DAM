package com.svalero.toplaptop.controller;

import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import com.svalero.toplaptop.exception.ErrorResponse;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.service.TechnicalService;
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
public class TechnicalController {

    @Autowired
    private TechnicalService technicalService;

    private final Logger logger = LoggerFactory.getLogger(TechnicalController.class);

    @GetMapping("/technicals")
    public ResponseEntity<List<Technical>> findAll() {
        logger.info("Inicio findAll technicals");
        List<Technical> technicals = technicalService.findAll();
        logger.info("Final findAll technicals");
        return new ResponseEntity<>(technicals, HttpStatus.OK);
    }

    @GetMapping("/technical/{id}")
    public ResponseEntity<Technical> findById(@PathVariable long id) throws TechnicalNotFoundException {
        logger.info("Inicio findById technicals");
        Technical technical = technicalService.findById(id);
        logger.info("Final findById technicals");
        return new ResponseEntity<>(technical, HttpStatus.OK);
    }

    @PostMapping("/technical")
    public ResponseEntity<Technical> addTechnical(@Valid @RequestBody Technical technical)   {
        logger.info("Inicio addTechnical");
        Technical newTechnical = technicalService.addTechnical(technical);
        logger.info("Final addTechnical");
        return new ResponseEntity<>(newTechnical, HttpStatus.CREATED);
    }

    @DeleteMapping("/technical/{id}")
    public ResponseEntity<Void> deleteTechnical(@PathVariable long id) throws TechnicalNotFoundException {
        logger.info("Inicio deleteTechnical");
        technicalService.deleteTechnical(id);
        logger.info("Final deleteTechnical");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/technical/{id}")
    public ResponseEntity<Technical> modifyTechnical(@PathVariable long id, @Valid @RequestBody Technical technical)
            throws TechnicalNotFoundException {
        logger.info("Inicio modifyTechnical");
        Technical newTechnical = technicalService.modifyTechnical(id, technical);
        logger.info("Final modifyTechnical");
        return new ResponseEntity<>(newTechnical, HttpStatus.OK);
    }

    @ExceptionHandler(TechnicalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTechnicalNotFoundException(TechnicalNotFoundException tnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", tnfe.getMessage());
        logger.error(tnfe.getMessage(), tnfe);
        logger.error(Arrays.toString(tnfe.getStackTrace()));
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
