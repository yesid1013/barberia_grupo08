package com.grupo08.barberia.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//identifica si esa persona tiene o no tiene permiso para acceder a una determinada información
public class CredencialesDTO {
    private String user;
    private String key; 
}
