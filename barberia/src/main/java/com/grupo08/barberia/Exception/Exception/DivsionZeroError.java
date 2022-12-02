package com.grupo08.barberia.Exception.Exception;

public class DivsionZeroError extends RuntimeException {//clase para tener un error personalizado que no controle el framework
    private static final long serialVersionUID = 1l;
    public DivsionZeroError(String message) {
        super(message);
    } 
    
}
