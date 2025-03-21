package com.iesbelen.dam.apirest.apithymeleaf.servidor.controladores;

import com.iesbelen.dam.apirest.apithymeleaf.servidor.modelo.entidades.Empleado;

import com.iesbelen.dam.apirest.apithymeleaf.servidor.servicios.ServicioEmpleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpleados {

    @Autowired
    ServicioEmpleados servicioEmpleados;

    @GetMapping
    public List<Empleado> buscarEmpleados(@RequestParam(name = "puesto", required = false) String puesto) {
        if (puesto == null)
            return servicioEmpleados.buscarEmpleados();
        else
            return servicioEmpleados.buscarEmpleadosPorPuesto(puesto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable(value = "id") int id){
        return servicioEmpleados.buscarEmpleadoPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardarEmpleado(@Validated @RequestBody Empleado empleado){
        return servicioEmpleados.guardarEmpleado(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable(value = "id") int id){
        return servicioEmpleados.eliminarEmpleado(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@Validated @RequestBody Empleado empleado, @PathVariable(value = "id") int id){
        return servicioEmpleados.actualizarEmpleado(empleado, id);
    }
}
