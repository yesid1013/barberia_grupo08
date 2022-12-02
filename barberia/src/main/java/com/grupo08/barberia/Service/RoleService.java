package com.grupo08.barberia.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo08.barberia.Entity.ERol;
import com.grupo08.barberia.Entity.Role;
import com.grupo08.barberia.Repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> finAll(){
        return (List<Role>) roleRepository.findAll();
    }

    public Optional<Role> findByNombre(ERol nombre){
        return roleRepository.findByNombre(nombre);
    }

    public Role save (Role role){
        return roleRepository.save(role);
    }
}
