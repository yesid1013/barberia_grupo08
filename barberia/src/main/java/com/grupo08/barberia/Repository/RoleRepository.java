package com.grupo08.barberia.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo08.barberia.Entity.ERol;
import com.grupo08.barberia.Entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,String> {
    public Optional<Role> findByNombre(ERol nombre);
}
