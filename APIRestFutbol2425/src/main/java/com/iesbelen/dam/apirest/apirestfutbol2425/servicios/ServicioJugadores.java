package com.iesbelen.dam.apirest.apirestfutbol2425.servicios;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.daos.IJugadorDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Equipo;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioJugadores {

    @Autowired
    IJugadorDAO jugadorDAO;


    public List<Jugador> buscarJugadorByNombreEquipo(String nombre) {
        return jugadorDAO.findJugadorByNombreEquipo(nombre);
    }
}
