package com.iesbelen.dam.apirest.appbiblioteca2425.controladores;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Usuario;
import com.iesbelen.dam.apirest.appbiblioteca2425.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @GetMapping
    public List<Usuario> buscarUsuarios() {
        return servicioUsuarios.buscarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable int id) {
        return servicioUsuarios.buscarUsuarioPorId(id);
    }
}
