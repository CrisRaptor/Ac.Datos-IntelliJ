package com.iesbelen.dam.apirest.apirestfutbol2425.controladores;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Jugador;
import com.iesbelen.dam.apirest.apirestfutbol2425.servicios.ServicioJugadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jugador")
public class ControladorJugadores {

    @Autowired
    ServicioJugadores servicioJugadores;

    @GetMapping("/{nombre}")
    public List<Jugador> buscarJugadorByNombreEquipo(@PathVariable(value = "nombre") String nombre) {
        return servicioJugadores.buscarJugadorByNombreEquipo(nombre);
    }
}
