package com.grupo08.barberia.Exception.Exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grupo08.barberia.Exception.Entity.ErrorResponse;
import com.grupo08.barberia.Exception.Entity.Error;

@RestControllerAdvice //Controlador de esos posibles errores que se pueden llegar a presentar
public class ValidationHandler { //validador de excepciones
    
    @ExceptionHandler
    protected ResponseEntity <ErrorResponse> handlerException(NumberFormatException ex){//Recibimos por parametros la excepcion que se esta presentando
        return new ResponseEntity<>(new ErrorResponse(406,"El dato suministrado no corresponde con el tipo de dato",new Date(),null),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    protected ResponseEntity <ErrorResponse> handlerException(HttpMessageNotReadableException ex){
        Error error= new Error("Falta de informacion","No viene el cuerpo");
        List<Error> listError= new ArrayList<>();
        listError.add(error);
        return new ResponseEntity<>(new ErrorResponse(400,"Datos",new Date(),listError),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity <ErrorResponse> handlerException(DivsionZeroError ex){//Error personalizado solo mando por parametros el tipo de error personalizado
        Error error= new Error("Dato incorrecto","No se puede dividir por cero");
        List<Error> listError= new ArrayList<>();
        listError.add(error);
        return new ResponseEntity<>(new ErrorResponse(400,"Datos",new Date(),listError),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity <ErrorResponse> handlerException(NoFoundException ex){
        Error error= new Error("Usuario incorrecto",ex.getMessage());
        List<Error> listError= new ArrayList<>();
        listError.add(error);
        return new ResponseEntity<>(new ErrorResponse(404,"Datos",new Date(),listError),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity <ErrorResponse> handlerException(InvalidDataException ex){
        List<Error> listError= new ArrayList<>();
        ex.getResult().getAllErrors().forEach((err)->{
            Error error= new Error();
            error.setField(((FieldError) err).getField());
            error.setMessage(err.getDefaultMessage());
            listError.add(error);
        });
        
        
        return new ResponseEntity<>(new ErrorResponse(404,"Error de data",new Date(),listError),HttpStatus.BAD_REQUEST);
    }
    
    
}
