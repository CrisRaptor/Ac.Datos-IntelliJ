package com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.controladores;

import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.daos.IEquipoDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.daos.IJugadorDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.daos.IPartidoDAO;
import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.entidades.Equipo;
import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.entidades.Jugador;
import com.iesbelen.dam.apirest.apirestfutbol2425thymeleaf.modelo.entidades.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    IEquipoDAO equipoDAO;
    @Autowired
    IPartidoDAO partidoDAO; 
    @Autowired
    IJugadorDAO jugadorDAO;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/verequipos")
    public String mostrarEquipos(Model model) {
        List<Equipo> equipos = (List<Equipo>) equipoDAO.findAll();
        model.addAttribute("equipos", equipos);
        return "verequipos";
    }

    @GetMapping("/verjugadores")
    public String mostrarJugadores(Model model) {
        List<Jugador> jugadores = (List<Jugador>) jugadorDAO.findAll();
        model.addAttribute("jugadores", jugadores);
        return "verjugadores";
    }

    @GetMapping("/verpartidos")
    public String mostrarPartidos(Model model) {
        List<Partido> partidos = (List<Partido>) partidoDAO.findAll();
        model.addAttribute("partidos", partidos);
        return "verpartidos";
    }

    @GetMapping("/verjugador")
    public String mostrarJugador(Model model, @RequestParam(name = "id", required = true) int id) {
        Optional<Jugador> jugador = jugadorDAO.findById(id);
        if (!jugador.isPresent()) {
            model.addAttribute("titulo", "Error");
            model.addAttribute("mensaje", "No se encontro el empleado con el id " + id);
            return "error";
        }
        model.addAttribute("jugador", jugador.get());
        return "verjugador";
    }

    @GetMapping("/altaequipo")
    public String altaEquipo(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "altaequipo";
    }

    @PostMapping("/altaequipo")
    public String crearDepartamento(@ModelAttribute Equipo equipo, Model model) {
        if (!equipoDAO.existsById(equipo.getId()))
        {
            equipoDAO.save(equipo);
            model.addAttribute("tipo_operacion", "ok");
            model.addAttribute("mensaje", "Departamento creado correctamente");
        }
        else {
            model.addAttribute("tipo_operacion", "error");
            model.addAttribute("mensaje", "Error al crear el departamento: clave duplicada");
        }
        return "altaequipo";
    }

}
