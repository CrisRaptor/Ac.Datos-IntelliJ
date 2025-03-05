package com.iesbelen.dam.apirest.appbiblioteca2425.servicios;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dao.IPrestamoDAO;
import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPrestamos {

    @Autowired
    private IPrestamoDAO prestamoDAO;

    public List<Prestamo> buscarPrestamos() {
        return (List<Prestamo>) prestamoDAO.findAll();
    }

    public ResponseEntity<Prestamo> guardarPrestamo(Prestamo prestamo) {
        if (!prestamoDAO.existsById(prestamo.getId())) {
            return ResponseEntity.ok().body(prestamoDAO.save(prestamo));
        } else {
            return ResponseEntity.badRequest().body(prestamo);
        }
    }
}
