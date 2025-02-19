package com.iesbelen.dam.apirest.apirestfutbol2425.modelos.daos;

import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Equipo;
import com.iesbelen.dam.apirest.apirestfutbol2425.modelos.entidades.Partido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPartidoDAO extends CrudRepository<Partido, Integer> {

    public List<Partido> getPartidosByEquipoLocal(Equipo equipo);

    public List<Partido> getPartidosByEquipoVisitante(Equipo equipo);
}
