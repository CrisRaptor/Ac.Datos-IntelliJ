package com.iesbelen.dam.apirest.appbiblioteca2425.controladores;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Libro;
import com.iesbelen.dam.apirest.appbiblioteca2425.servicios.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class ControladorLibros {

    @Autowired
    private ServicioLibros servicioLibros;

    @GetMapping
    public List<Libro> buscarLibros(){
        return servicioLibros.buscarLibros();
    }

    @PostMapping
    public ResponseEntity<Libro> insertarLibro(@Validated @RequestBody Libro libro){
        return servicioLibros.guardarLibro(libro);
    }
}
