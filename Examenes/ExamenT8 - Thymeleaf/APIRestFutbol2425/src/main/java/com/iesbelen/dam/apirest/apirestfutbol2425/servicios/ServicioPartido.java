package com.iesbelen.dam.apirest.apirestfutbol2425.servicios;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.daos.IEquipoDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.daos.IPartidoDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.dtos.PartidoDTO;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Equipo;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioPartido {

    @Autowired
    IPartidoDAO partidoDAO;
    IEquipoDAO equipoDAO;

    public ResponseEntity<Partido> guardarPartidoDTO(PartidoDTO partidoDTO) {
        Partido partido = new Partido();

        Equipo equipoLocal = new Equipo();
        equipoLocal.setId(equipoDAO.findByNombre(partidoDTO.getNombreEquipoVisitante()).getId());
        equipoLocal.setNombre(partidoDTO.getNombreEquipoLocal());
        equipoLocal.setEscudo(partidoDTO.getEscudoEquipoLocal());
        Equipo equipoVisitante = new Equipo();
        equipoVisitante.setId(equipoDAO.findByNombre(partidoDTO.getNombreEquipoVisitante()).getId());
        equipoVisitante.setNombre(partidoDTO.getNombreEquipoVisitante());
        equipoVisitante.setEscudo(partidoDTO.getEscudoEquipoVisitante());

        partido.setId(10);
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setFecha(LocalDate.parse(partidoDTO.getFecha()));
        partido.setHora(LocalTime.parse(partidoDTO.getHora()));
        partido.setGolesLocal(partidoDTO.getGolesEquipoLocal());
        partido.setGolesVisitante(partidoDTO.getGolesEquipoVisitante());
        return ResponseEntity.ok().body(partidoDAO.save(partido));
    }

    public List<Partido> mostrarPartidosByEquipo(Equipo equipo) {
        List<Partido> partidosEquipoLocal = partidoDAO.getPartidosByEquipoLocal(equipo);
        List<Partido> partidosEquipoVisitante = partidoDAO.getPartidosByEquipoVisitante(equipo);
        List<Partido> partidos = new ArrayList<>();
        for (Partido partido : partidosEquipoLocal) {
            partidos.add(partido);
        }
        for (Partido partido : partidosEquipoVisitante) {
            partidos.add(partido);
        }
        return partidos;
    }

    public List<Partido> mostrarPartidosGanadosByEquipoLocal() {
        List<Partido> partidos = (List<Partido>) partidoDAO.findAll();
        List<Partido> partidosGanadosLocal = new ArrayList<>();
        for (Partido partido : partidos) {
            if (partido.getGolesLocal() > partido.getGolesVisitante()) {
                partidosGanadosLocal.add(partido);
            }
        }
        return partidosGanadosLocal;
    }
}
