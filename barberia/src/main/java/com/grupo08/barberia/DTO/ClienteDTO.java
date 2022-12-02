package com.grupo08.barberia.DTO;

import java.util.List;

import com.grupo08.barberia.Entity.Role;

import lombok.Data;

@Data
public class ClienteDTO {
    // Capa para transferir los datos que yo quiera y no mostrar datos sensibles en el endpoint de listar
    // Definimos los datos que solo queremos mostrar en este ejemplo no mostramos el dato de password
    private String id;
    private String nombre_cliente;
    private String apellido_cliente;
    private String username;
    private List<Role> roles;
}
