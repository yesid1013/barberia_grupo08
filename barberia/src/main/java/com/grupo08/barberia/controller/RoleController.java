package com.grupo08.barberia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo08.barberia.Entity.Role;
import com.grupo08.barberia.Service.RoleService;

@RestController
@RequestMapping("/api/v1/rol")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/crear")
    public Role save(@RequestBody Role role){
        return roleService.save(role);
    }
    
}
