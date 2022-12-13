package com.grupo08.barberia.DTO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CrearClienteDTO {
    private String id;
    
    @Size(min=5,message = "El campo nombre requiere minimo 5 caracteres")
    private String nombre_cliente;
    private String apellido_cliente;
    @Email(message="El campo usuario no tiene la estructura de un correo electronico")
    private String username;
    @NotEmpty
    private String password;
    private Set<String> roles;
}
