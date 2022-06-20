package com.svalero.toplaptop.exception;

public class OrderNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Orden no encontrada";

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
