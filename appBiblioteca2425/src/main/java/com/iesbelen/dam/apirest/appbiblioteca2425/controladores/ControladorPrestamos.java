package com.iesbelen.dam.apirest.appbiblioteca2425.controladores;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Prestamo;
import com.iesbelen.dam.apirest.appbiblioteca2425.servicios.ServicioPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class ControladorPrestamos {

    @Autowired
    private ServicioPrestamos servicioPrestamos;

    @GetMapping
    public List<Prestamo> buscarPrestamos() {
        return servicioPrestamos.buscarPrestamos();
    }

    @PostMapping()
    public ResponseEntity<Prestamo> guardarPrestamo(@Validated @RequestBody Prestamo prestamo) {
        return servicioPrestamos.guardarPrestamo(prestamo);
    }
}
