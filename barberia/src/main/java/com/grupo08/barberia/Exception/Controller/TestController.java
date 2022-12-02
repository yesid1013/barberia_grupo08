package com.grupo08.barberia.Exception.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo08.barberia.DTO.ClienteDTO;
import com.grupo08.barberia.Entity.Cliente;
import com.grupo08.barberia.Exception.Exception.DivsionZeroError;
import com.grupo08.barberia.Exception.Exception.NoFoundException;
import com.grupo08.barberia.Service.ClienteService;
import com.grupo08.barberia.Utility.ConvertEntity;

@RestController
@RequestMapping("/test")
@CrossOrigin("*") //Permite que cualquier servidor se conecte
public class TestController {
    @Autowired
    ClienteService clienteService;

    ConvertEntity convertEntity= new ConvertEntity();

    @GetMapping("/operacion/{num1}/{num2}")
    public Double operar(@PathVariable double num1,@PathVariable double num2,@RequestBody String operacion){
        switch(operacion){
            case "+":
                return num1+num2;
            case "-":
                return num1-num2;
            case "*":
                return num1*num2;
            case "/":
                if(num2==0){
                    throw new DivsionZeroError("Divisor en cero");
                }
                return num1/num2;
        }   
        return num1+num2;

    }

    @GetMapping("/cliente/{username}")
    public ClienteDTO findByUsername(@PathVariable String username){
        Cliente cliente =clienteService.findByUsername(username);

        if(cliente==null){
            throw new NoFoundException("El usuario con username: "+username+" No existe");
        }

        return (ClienteDTO) convertEntity.convert(cliente, new ClienteDTO());
         
    }

    
}
