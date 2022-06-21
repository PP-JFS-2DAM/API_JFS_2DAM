package com.svalero.toplaptop.controller;

import com.svalero.toplaptop.domain.Receipt;
import com.svalero.toplaptop.domain.dto.ReceiptDTO;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.ReceiptNotFoundException;
import com.svalero.toplaptop.exception.ErrorResponse;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.service.ReceiptService;
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
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    private final Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    @GetMapping("/receipts")
    public ResponseEntity<Flux<Receipt>> findAll() {
        logger.info("Inicio findAll receipts");
        Flux<Receipt> receipts = receiptService.findAll();
        logger.info("Final findAll receipts");
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<Mono<Receipt>> findById(@PathVariable long id) throws ReceiptNotFoundException {
        logger.info("Inicio findById receipts");
        Mono<Receipt> receipt = receiptService.findById(id);
        logger.info("Final findById receipts");
        return ResponseEntity.ok(receipt);
    }

    @PostMapping("/receipt")
    public ResponseEntity<?> addReceipt(@Valid @RequestBody ReceiptDTO receiptDTO) throws OrderNotFoundException {
        logger.info("Inicio addReceipt");
        Mono<Receipt> receipt = receiptService.addReceipt(receiptDTO);
        logger.info("Final addReceipt");
        return ResponseEntity.ok(receipt.block());
    }

    @DeleteMapping("/receipt/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable long id) throws ReceiptNotFoundException {
        logger.info("Inicio deleteReceipt");
        receiptService.deleteReceipt(id);
        logger.info("Final deleteReceipt");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/receipt/{id}")
    public ResponseEntity<Mono<Receipt>> modifyReceipt(@PathVariable long id, @Valid @RequestBody ReceiptDTO receiptDTO)
            throws ReceiptNotFoundException, OrderNotFoundException {
        logger.info("Inicio modifyReceipt");
        Mono<Receipt> receipt = receiptService.modifyReceipt(id, receiptDTO);
        logger.info("Final modifyReceipt");
        return ResponseEntity.ok(receipt);
    }

    @ExceptionHandler(ReceiptNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReceiptNotFoundException(ReceiptNotFoundException rnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        logger.error(Arrays.toString(rnfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException onfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", onfe.getMessage());
        logger.error(onfe.getMessage(), onfe);
        logger.error(Arrays.toString(onfe.getStackTrace()));
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
