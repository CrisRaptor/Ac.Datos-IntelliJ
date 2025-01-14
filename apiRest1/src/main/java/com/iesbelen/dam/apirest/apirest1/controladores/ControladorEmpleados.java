package com.iesbelen.dam.apirest.apirest1.controladores;

import com.iesbelen.dam.apirest.apirest1.modelo.dao.IEmpleadosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpleados {

    @Autowired
    IEmpleadosDAO empleadosDAO;

    @GetMapping
    public List<Empleado> buscarEmpleados(){
        return (List<Empleado>) empleadosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable(value = "id") int id){
        Optional<Empleado> empleado = empleadosDAO.findById(id);
        if(empleado.isPresent()) {
            return ResponseEntity.ok().body(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Empleado guardarEmpleado(@Validated @RequestBody Empleado empleado){
        return empleadosDAO.save(empleado);
    }
}
