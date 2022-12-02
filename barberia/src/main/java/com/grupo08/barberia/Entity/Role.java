package com.grupo08.barberia.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role { //Entidad que constuye el Role
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String idRole;

    @Enumerated(EnumType.STRING)//  los datos que llegan de la entity ERol seran tipo string
    private ERol nombre;

    public Role(ERol nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Role [idRole=" + idRole + ", nombre=" + nombre + "]";
    }

    
}
