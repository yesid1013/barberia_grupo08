package com.grupo08.barberia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo08.barberia.Entity.Barbero;
import com.grupo08.barberia.Entity.Message;
import com.grupo08.barberia.Repository.BarberoRepository;

@Service
public class BarberoService {
    @Autowired
    BarberoRepository barberoRepository;

    public Barbero save(Barbero barbero){
        return barberoRepository.save(barbero);
    }

    public List<Barbero> findAll(){
        return (List<Barbero>) barberoRepository.findAll();
    }

    public Message deleteById(String id){
        try {
            barberoRepository.deleteById(id);
            return new Message(200, "Registro eliminado");
        } catch (Exception e) {
            return new Message(400, "No existe Registro con id "+id);
        }
        
    }

    public Message update(Barbero barbero){
        
        try {
            barberoRepository.findById(barbero.getId_barbero()).get();
            return new Message(200, "Encontrado");
            
        } catch (Exception e) {
            return new Message(404, "Barbero no encontrado");
        }
        
        
    }
}
