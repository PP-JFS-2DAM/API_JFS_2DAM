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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public ResponseEntity<Flux<Technical>> findAll() {
        logger.info("Inicio findAll technicals");
        Flux<Technical> technicals = technicalService.findAll();
        logger.info("Final findAll technicals");
        return ResponseEntity.ok(technicals);
    }

    @GetMapping("/technical/{id}")
    public ResponseEntity<Mono<Technical>> findById(@PathVariable long id) throws TechnicalNotFoundException {
        logger.info("Inicio findById technicals");
        Mono<Technical> technical = technicalService.findById(id);
        logger.info("Final findById technicals");
        return ResponseEntity.ok(technical);
    }

    @PostMapping("/technical")
    public ResponseEntity<?> addTechnical(@Valid @RequestBody Technical technical)   {
        logger.info("Inicio addTechnical");
        Mono<Technical> newTechnical = technicalService.addTechnical(technical);
        logger.info("Final addTechnical");
        return ResponseEntity.ok(newTechnical.block());
    }

    @DeleteMapping("/technical/{id}")
    public ResponseEntity<Mono<Technical>> deleteTechnical(@PathVariable long id) throws TechnicalNotFoundException {
        logger.info("Inicio deleteTechnical");
        Mono<Technical> technical = technicalService.deleteTechnical(id);
        logger.info("Final deleteTechnical");
        return ResponseEntity.ok(technical);
    }

    @PutMapping("/technical/{id}")
    public ResponseEntity<Mono<Technical>> modifyTechnical(@PathVariable long id, @Valid @RequestBody Technical technical)
            throws TechnicalNotFoundException {
        logger.info("Inicio modifyTechnical");
        Mono<Technical> newTechnical = technicalService.modifyTechnical(id, technical);
        logger.info("Final modifyTechnical");
        return ResponseEntity.ok(newTechnical);
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
