package com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dao;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Prestamo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrestamoDAO extends CrudRepository<Prestamo, Integer> {
}
