package com.onlinenachhilfe.backend.exception;

public class InvalidCredentialsException extends RuntimeException {

    // Konstruktor ohne Nachricht
    public InvalidCredentialsException() {
        super();
    }

    // Konstruktor mit Nachricht
    public InvalidCredentialsException(String message) {
        super(message);
    }

    // Konstruktor mit Nachricht und Ursache
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    // Konstruktor mit Ursache
    public InvalidCredentialsException(Throwable cause) {
        super(cause);
    }
}
