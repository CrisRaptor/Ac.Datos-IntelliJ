package com.iesbelen.dam.apirest.apirest1.controladores;

import com.iesbelen.dam.apirest.apirest1.modelo.dao.IDepartamentosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.dao.IEmpleadosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.dto.EmpleadoDTO;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Departamento;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Empleado;

import com.iesbelen.dam.apirest.apirest1.servicios.ServicioEmpleados;
import org.modelmapper.ModelMapper;
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

    //DTO
    @GetMapping("dto/{id}")
    public ResponseEntity<EmpleadoDTO> buscarEmpleadosDTOPorId(@PathVariable(value = "id") int id){
        return servicioEmpleados.buscarEmpleadosDTOPorId(id);
    }
}
