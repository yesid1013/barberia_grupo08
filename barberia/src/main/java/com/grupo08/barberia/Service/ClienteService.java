package com.grupo08.barberia.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.grupo08.barberia.Entity.Cliente;
import com.grupo08.barberia.Entity.Message;
import com.grupo08.barberia.Repository.ClieneRepository;
import com.grupo08.barberia.Security.Hash;



@Service
public class ClienteService {
    @Autowired
    ClieneRepository clieneRepository;

    public Cliente save(Cliente cliente){
        return clieneRepository.save(cliente);
    }

    public List<Cliente> findAll(){
        return (List<Cliente>) clieneRepository.findAll();
    }

    public Message deleteById(String id){
        try {
            clieneRepository.deleteById(id);
            return new Message(200, "Registro eliminado");
        } catch (Exception e) {
            return new Message(400, "No existe Registro con id "+id);
        }
        
    }

    public Message update(Cliente cliente){
        
        try {
            clieneRepository.findById(cliente.getId()).get();
            cliente.setPassword(Hash.sha1(cliente.getPassword()));
            clieneRepository.save(cliente);
            return new Message(200, "Actualizado");
            
        } catch (Exception e) {
            return new Message(404, "Cliente no encontrado");
        }
    }

    public Cliente login(String user,String pwd){
        return clieneRepository.login(user, pwd);
    }

    public Cliente findByUsername(String user){
        return clieneRepository.findByUsername(user);
    }

    public Boolean validarCredenciales(String user,String key){
        Cliente clienteC=clieneRepository.findByUsername(user);
        if (clienteC==null) {
            return false;
            
        } else {
            if (Hash.sha1(clienteC.getPassword()+Hash.sha1(user)).equals(key)) {
                return true;
            }else {
                return false;
            }   
            
        }
    }
}
