package com.iesbelen.dam.apirest.apirestfutbol2425.servicios;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.daos.IEquipoDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEquipos {

    @Autowired
    IEquipoDAO equipoDAO;

    public List<Equipo> buscarEquipos() {
        return (List<Equipo>) equipoDAO.findAll();
    }

    public ResponseEntity<Equipo> buscarEquipoById(int id) {
        Optional<Equipo> equipo = equipoDAO.findById(id);
        if (equipo.isPresent()) {
            return ResponseEntity.ok().body(equipo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Equipo> guardarEquipo(@Validated @RequestBody Equipo equipo) {
        if (!equipoDAO.existsById(equipo.getId())) {
            return ResponseEntity.ok().body(equipoDAO.save(equipo));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    public ResponseEntity<?> eliminarEquipo(int id){
        if (equipoDAO.existsById(id)) {
            equipoDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> modificarEquipo(@Validated @RequestBody Equipo equipo, @PathVariable(value = "id") int id){
        if(equipoDAO.existsById(id)) {
            equipoDAO.save(equipo);
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
