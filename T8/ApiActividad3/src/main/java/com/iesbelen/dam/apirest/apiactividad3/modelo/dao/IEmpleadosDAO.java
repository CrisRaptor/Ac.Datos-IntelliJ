package com.iesbelen.dam.apirest.apiactividad3.modelo.dao;

import com.iesbelen.dam.apirest.apiactividad3.modelo.entidades.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadosDAO extends CrudRepository<Empleado, Integer> {
    Empleado findByPuesto(String nombre);
    List<Empleado> findByPuestoContains(String string);
    //Empleado findByDepnoGreaterThanEqual(int depno);

    @Query("select e from Empleado e where e.nombre like %:patron%")
    Empleado findByNombre(@Param("patron") String patron);

    ////// Ejercicios
    //4 Busqueda de empleador por nombre, especificando cadena como prefijo
    Empleado findByNombreStartsWith(String prefijo);

    //5 Busqueda de empleado por puesto, una subcadena como parametro
    Empleado findByNombreContains(String subcadena);

}
