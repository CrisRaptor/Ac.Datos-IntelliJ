package com.iesbelen.dam.apirest.apirestfutbol2425.controladores;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.dtos.PartidoDTO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Partido;
import com.iesbelen.dam.apirest.apirestfutbol2425.servicios.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partidodto")
public class ControladorPartidoDTO {

    @Autowired
    ServicioPartido servicioPartido;

    @PostMapping
    public ResponseEntity<Partido> guardarPartidoDTO(@RequestBody @Validated PartidoDTO partidoDTO) {
        return servicioPartido.guardarPartidoDTO(partidoDTO);
    }
}
