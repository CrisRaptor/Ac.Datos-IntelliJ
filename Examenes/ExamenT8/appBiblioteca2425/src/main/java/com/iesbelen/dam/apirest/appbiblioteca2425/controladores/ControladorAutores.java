package com.iesbelen.dam.apirest.appbiblioteca2425.controladores;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Autor;
import com.iesbelen.dam.apirest.appbiblioteca2425.servicios.ServicioAutores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class ControladorAutores {

    @Autowired
    private ServicioAutores servicioAutores;

    @GetMapping
    public List<Autor> buscarAutores() {
        return servicioAutores.buscarAutores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutor(@PathVariable(value = "id") int id) {
        return servicioAutores.buscarAutorPorId(id);
    }

    @PostMapping
    public ResponseEntity<Autor> guardarAutor(@Validated @RequestBody Autor autor) {
        return servicioAutores.guardarAutor(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAutor(@PathVariable(value = "id") int id) {
        return servicioAutores.eliminarAutor(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarAutor(@Validated @RequestBody Autor autor, @PathVariable(value = "id") int id) {
        return servicioAutores.actualizarAutor(autor,id);
    }
}
