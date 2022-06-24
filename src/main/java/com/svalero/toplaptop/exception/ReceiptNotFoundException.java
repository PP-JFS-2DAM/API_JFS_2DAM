package com.svalero.toplaptop.exception;

public class ReceiptNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Factura no encontrada";

    public ReceiptNotFoundException(String message) {
        super(message);
    }

    public ReceiptNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
