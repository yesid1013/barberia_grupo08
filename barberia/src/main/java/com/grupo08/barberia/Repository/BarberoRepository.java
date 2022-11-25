package com.grupo08.barberia.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo08.barberia.Entity.Barbero;

@Repository
public interface BarberoRepository extends CrudRepository<Barbero,String> {
    public Optional<Barbero> findById(String id);
}
