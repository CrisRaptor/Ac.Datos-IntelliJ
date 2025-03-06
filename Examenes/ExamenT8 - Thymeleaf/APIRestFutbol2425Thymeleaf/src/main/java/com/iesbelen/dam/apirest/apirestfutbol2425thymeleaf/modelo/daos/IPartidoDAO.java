package com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.daos;

import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.entidades.Partido;
import org.springframework.data.repository.CrudRepository;

public interface IPartidoDAO extends CrudRepository<Partido, Integer> {
//    @Query("select p from Partido p where ...")
//    List<Partido> getPartidosGanadosByEquipoLocal(@Param("equipoLocal")Equipo equipo);

//    @Query("select p from Partido p where p.equipoLocal.id like %id%")
//    List<Partido> getPartidosByEquipoLocal(@Param("id")Equipo equipo);
}
