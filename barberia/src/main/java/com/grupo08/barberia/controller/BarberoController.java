package com.grupo08.barberia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo08.barberia.Entity.Barbero;
import com.grupo08.barberia.Entity.Message;
import com.grupo08.barberia.Service.BarberoService;

@RestController
@RequestMapping("/api/v1/barbero")
public class BarberoController {
    @Autowired
    BarberoService barberoService;

    @PostMapping("/crear")
    public ResponseEntity <Message> save(@RequestBody Barbero barbero){
        try {
            barberoService.save(barbero);
            return new ResponseEntity<Message>(new Message(201,"Registro creado"), HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(400,"Los datos ingresados no son correctos"),HttpStatus.BAD_REQUEST);
        }
        
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable String id){
        Message message=barberoService.deleteById(id);
        return new ResponseEntity<>(message,HttpStatus.resolve(message.getStatus()));
    }

    @PutMapping("/actualizar")
    public ResponseEntity <Message> update(@RequestBody Barbero barbero){
        try {
            Message message=barberoService.update(barbero);
            //return new ResponseEntity<Message>(new Message(message.getStatus(),message.getMessage()), HttpStatus.valueOf(message.getStatus()));
            return new ResponseEntity<>(message,HttpStatus.resolve(message.getStatus()));
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(400,"Ha ocurrido un error "+e),HttpStatus.BAD_REQUEST);
        }
        
    }
}
