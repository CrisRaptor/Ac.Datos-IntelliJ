package com.iesbelen.dam.apirest.appbiblioteca2425.servicios;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dao.IUsuarioDAO;
import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuarios {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    public List<Usuario> buscarUsuarios() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    public ResponseEntity<Usuario> buscarUsuarioPorId(Integer id) {
        Optional<Usuario> usuario = usuarioDAO.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
