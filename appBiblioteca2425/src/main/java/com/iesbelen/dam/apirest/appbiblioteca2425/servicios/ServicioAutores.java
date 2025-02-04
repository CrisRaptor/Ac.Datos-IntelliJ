package com.iesbelen.dam.apirest.appbiblioteca2425.servicios;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dao.IAutorDAO;
import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dto.AutorDTO;
import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAutores {

    @Autowired
    private IAutorDAO autorDAO;

    public List<Autor> buscarAutores(){
        return (List<Autor>) autorDAO.findAll();
    }

    public ResponseEntity<Autor> buscarAutorPorId(Integer id){
        Optional<Autor> autor = autorDAO.findById(id);
        if(autor.isPresent()){
            return ResponseEntity.ok().body(autor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Autor> guardarAutor(Autor autor){
        if (!autorDAO.existsById(autor.getId())) {
            return ResponseEntity.ok().body(autorDAO.save(autor));
        } else {
            return ResponseEntity.badRequest().body(autor);
        }
    }

    public ResponseEntity<?> actualizarAutor(Autor autor, int id){
        if(autorDAO.existsById(id)) {
            autorDAO.save(autor);
            return ResponseEntity.ok().body("Autor actualizado con id "+id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> eliminarAutor(Integer id){
        if (autorDAO.existsById(id)) {
            autorDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado autor con id "+id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> guardarAutorDTO(AutorDTO autorDTO){
        if (!autorDAO.existsById(autorDTO.getId())){
            Autor newAutorDTO = new Autor();
            newAutorDTO.setId(autorDTO.getId());
            newAutorDTO.setPais(autorDTO.getPais());
            newAutorDTO.setNombreAutor(autorDTO.getNombreAutor());
            return ResponseEntity.ok().body(autorDAO.save(newAutorDTO));
        } else {
            return ResponseEntity.badRequest().body("No se pudo insertar el autor");
        }
    }
}
