package com.svalero.toplaptop.exception;

public class TechnicalNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Técnico/a no encontrado/a";

    public TechnicalNotFoundException(String message) {
        super(message);
    }

    public TechnicalNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
