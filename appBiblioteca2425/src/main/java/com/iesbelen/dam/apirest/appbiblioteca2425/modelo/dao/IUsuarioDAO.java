package com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dao;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario, Integer> {
}
