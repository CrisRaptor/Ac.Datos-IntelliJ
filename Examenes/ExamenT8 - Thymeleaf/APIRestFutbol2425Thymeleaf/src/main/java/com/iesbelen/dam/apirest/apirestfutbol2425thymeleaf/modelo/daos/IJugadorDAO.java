package com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.daos;

import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.entidades.Jugador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IJugadorDAO extends CrudRepository<Jugador, Integer> {

    @Query("select j from Jugador j where j.equipo.nombre like %:nombre%")
    List<Jugador> findJugadorByNombreEquipo(@Param("nombre") String nombre);
}
