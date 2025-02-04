package com.iesbelen.dam.apirest.appbiblioteca2425.servicios;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dao.ILibroDAO;
import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioLibros {

    @Autowired
    private ILibroDAO libroDAO;

    public List<Libro> buscarLibros(){
        return (List<Libro>) libroDAO.findAll();
    }

    public ResponseEntity<Libro> guardarLibro(Libro libro){
        if (!libroDAO.existsById(libro.getId())) {
            return ResponseEntity.ok().body(libroDAO.save(libro));
        } else {
            return ResponseEntity.badRequest().body(libro);
        }
    }
}
