package com.svalero.toplaptop.exception;

public class UserNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Usuario no encontrado";

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
