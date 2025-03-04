package com.iesbelen.dam.apirest.apirestfutbol2425.controladores;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.daos.IEquipoDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Equipo;
import com.iesbelen.dam.apirest.apirestfutbol2425.servicios.ServicioEquipos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipo")
public class ControladorEquipos {

    @Autowired
    ServicioEquipos servicioEquipos;

    @GetMapping
    public List<Equipo> buscarEquipos() {
        return servicioEquipos.buscarEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscarEquipoPorId(@PathVariable(value = "id") int id){
        return servicioEquipos.buscarEquipoById(id);
    }

    @PostMapping
    public ResponseEntity<Equipo> guardarEquipo(@RequestBody Equipo equipo){
        return servicioEquipos.guardarEquipo(equipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEquipo(@PathVariable(value = "id") int id){
        return servicioEquipos.eliminarEquipo(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEquipo(@PathVariable(value = "id") int id, @RequestBody Equipo equipo){
        return servicioEquipos.modificarEquipo(equipo,id);
    }
}
