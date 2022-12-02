package com.grupo08.barberia.Exception.Exception;

public class NoFoundException extends RuntimeException {
    private static final long serialVersionUID = 1l;
    public NoFoundException(String message) {
        super(message);
    } 
}
