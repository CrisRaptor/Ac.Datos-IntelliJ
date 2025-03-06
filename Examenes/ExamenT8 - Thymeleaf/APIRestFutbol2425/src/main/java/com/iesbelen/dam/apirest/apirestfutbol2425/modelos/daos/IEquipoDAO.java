package com.iesbelen.dam.apirest.apirestfutbol2425.modelos.daos;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Equipo;
import org.springframework.data.repository.CrudRepository;

public interface IEquipoDAO extends CrudRepository<Equipo, Integer> {

    public Equipo findByNombre(String nombre);
}
