package com.grupo08.barberia.controller;



import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo08.barberia.Entity.Mensaje;

@RestController
@RequestMapping("/prueba")
public class prueba {
    
    //primera forma de recibir
    @GetMapping
    public String saludar(@RequestBody Mensaje mensaje){
        return "Hola "+mensaje.getNombre()+" "+mensaje.getSaludo();
    }

    //recibir variable por url
    /*@GetMapping("/{dia}")
    public String saludar2(@RequestBody Mensaje mensaje, @PathVariable String dia){
        return "Hola "+mensaje.getNombre()+" "+mensaje.getSaludo()+""+dia;
    }*/

    @GetMapping("/{dia}")
    public String saludar3(@RequestBody Mensaje mensaje, @PathVariable String dia,@Param("dato") String dato){
        return "Hola "+mensaje.getNombre()+" "+mensaje.getSaludo()+" "+dia+" "+dato;
    }
}
