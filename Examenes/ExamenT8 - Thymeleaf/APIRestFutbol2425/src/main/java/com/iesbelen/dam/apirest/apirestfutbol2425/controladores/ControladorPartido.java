package com.iesbelen.dam.apirest.apirestfutbol2425.controladores;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.dtos.PartidoDTO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Equipo;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Partido;
import com.iesbelen.dam.apirest.apirestfutbol2425.servicios.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partido")
public class ControladorPartido {

    @Autowired
    ServicioPartido servicioPartido;

    @GetMapping
    public List<Partido> mostrarPartidosByEquipo(@Validated @RequestBody Equipo equipo) {
        return servicioPartido.mostrarPartidosByEquipo(equipo);
    }

    @GetMapping("/victoria_local")
    public List<Partido> mostrarPartidosGanadosByEquipoLocal() {
        return servicioPartido.mostrarPartidosGanadosByEquipoLocal();
    }
}
