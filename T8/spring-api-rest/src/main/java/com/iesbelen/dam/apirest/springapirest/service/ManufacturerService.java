package com.iesbelen.dam.apirest.springapirest.service;


import com.iesbelen.dam.apirest.springapirest.model.Manufacturer;

import java.util.List;
import java.util.Optional;

/*
CRUD
Create
Read
Update
Delete
 */
public interface ManufacturerService {

    // Métodos de consulta (READ)
    List<Manufacturer> findAll();
    List<Manufacturer> findAllByYear(Integer year);
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> findByName(String name);

    // CREATE / UPDATE
    Manufacturer save(Manufacturer manufacturer);

    // DELETE
    void deleteById(Long id);
    void deleteAll();

    // Más lógica de negocio:
    // 1. Coches fabricados
    // 2. Beneficios obtenidos
    // 3. ......
}
