package com.grupo08.barberia.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.grupo08.barberia.Entity.Cliente;

@Repository
public interface ClieneRepository extends CrudRepository<Cliente,String> {
    public Optional<Cliente> findById(String id);

    public Cliente findByUsername(String user);

    //Creacion de consulta personalizada
    @Transactional(readOnly = true) //No va a afectar la presistencia de la base de datos, solo es una consulta
    @Query(value = "Select * from clientes where username=:user and password=:pwd",nativeQuery = true) //Consulta
    public Cliente login(@Param("user") String user, @Param("pwd") String pwd); //metodo con parametros que necesita la consulta
}
