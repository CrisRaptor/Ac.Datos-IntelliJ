package com.iesbelen.dam.apirest.apiactividad3.modelo.dao;

import com.iesbelen.dam.apirest.apiactividad3.modelo.entidades.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentosDAO extends CrudRepository<Departamento, Integer> {

    ////// Ejercicios
    //1 Busqueda por nombre
    Departamento findByNombre(String nombre);

    //2 Busqueda por ubicacion
    Departamento findByUbicacion(String ubicacion);

    //3 Busqueda ignorando mayus y minus
    Departamento findByNombreIgnoreCase(String nombre);
    Departamento findByUbicacionIgnoreCase(String ubicacion);
}
