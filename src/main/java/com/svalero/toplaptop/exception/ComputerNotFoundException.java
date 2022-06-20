package com.svalero.toplaptop.exception;

public class ComputerNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Ordenador no encontrada";

    public ComputerNotFoundException(String message) {
        super(message);
    }

    public ComputerNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }


}
