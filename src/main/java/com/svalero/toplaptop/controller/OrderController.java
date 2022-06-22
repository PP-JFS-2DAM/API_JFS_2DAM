package com.svalero.toplaptop.controller;

import com.svalero.toplaptop.domain.WorkOrder;
import com.svalero.toplaptop.exception.*;
import com.svalero.toplaptop.service.OrderService;
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
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/orders")
    public ResponseEntity<List<WorkOrder>> findAll() {
        logger.info("Inicio findAll work_orders");
        List<WorkOrder> workOrders = orderService.findAll();
        logger.info("Final findAll workOrders");
        return new ResponseEntity<>(workOrders, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<WorkOrder> findById(@PathVariable long id) throws OrderNotFoundException {
        logger.info("Inicio findById workOrders");
        WorkOrder workOrder = orderService.findById(id);
        logger.info("Final findById workOrders");
        return new ResponseEntity<>(workOrder, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<WorkOrder> addOrder(@Valid @RequestBody com.svalero.toplaptop.domain.dto.WorkOrderDTO workOrderDTO) throws ComputerNotFoundException, TechnicalNotFoundException {
        logger.info("Inicio addOrder");
        WorkOrder workOrder = orderService.addOrder(workOrderDTO);
        logger.info("Final addOrder");
        return new ResponseEntity<>(workOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) throws OrderNotFoundException {
        logger.info("Inicio deleteOrder");
        orderService.deleteOrder(id);
        logger.info("Final deleteOrder");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<WorkOrder> modifyOrder(@PathVariable long id, @Valid @RequestBody com.svalero.toplaptop.domain.dto.WorkOrderDTO workOrderDTO)
            throws OrderNotFoundException, ComputerNotFoundException, TechnicalNotFoundException {
        logger.info("Inicio modifyOrder");
        WorkOrder workOrder = orderService.modifyOrder(id, workOrderDTO);
        logger.info("Final modifyOrder");
        return new ResponseEntity<>(workOrder, HttpStatus.OK);
    }


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException onfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", onfe.getMessage());
        logger.error(onfe.getMessage(), onfe);
        logger.error(Arrays.toString(onfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ComputerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(ComputerNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        logger.error(Arrays.toString(cnfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TechnicalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(TechnicalNotFoundException tnfe) {
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
