package com.iesbelen.dam.apirest.apithymeleaf.servidor.servicios;

import com.iesbelen.dam.apirest.apithymeleaf.servidor.modelo.dao.IEmpleadosDAO;
import com.iesbelen.dam.apirest.apithymeleaf.servidor.modelo.entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEmpleados {

    @Autowired
    private IEmpleadosDAO empleadosDAO;

    @GetMapping
    public List<Empleado> buscarEmpleados() {
        return (List<Empleado>) empleadosDAO.findAll();
    }

    public ResponseEntity<Empleado> buscarEmpleadoPorId(int id){
        Optional<Empleado> empleado = empleadosDAO.findById(id);
        if(empleado.isPresent()) {
            return ResponseEntity.ok().body(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Empleado> buscarEmpleadosPorPuesto(String puesto){
        return empleadosDAO.findByPuestoContains(puesto);
    }

    @PostMapping
    public ResponseEntity<?> guardarEmpleado(@Validated @RequestBody Empleado empleado){
        if (!empleadosDAO.existsById(empleado.getId())) {
            return ResponseEntity.ok().body(empleadosDAO.save(empleado));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable(value = "id") int id){
        if (empleadosDAO.existsById(id)) {
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@Validated @RequestBody Empleado empleado, @PathVariable(value = "id") int id){
        if(empleadosDAO.existsById(id)) {
            empleadosDAO.save(empleado);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
