package com.iesbelen.dam.apirest.apiactividad3.controladores;

import com.iesbelen.dam.apirest.apiactividad3.modelo.entidades.Departamento;
import com.iesbelen.dam.apirest.apiactividad3.servicios.ServicioDepartamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@RepositoryRestResource(collectionResourceRel = "departamentos", path = "departamentos")
public class ControladorDepartamentos {

    @Autowired
    ServicioDepartamentos servicioDepartamentos;

    @GetMapping
    public List<Departamento> buscarDepartamentos(){
        return servicioDepartamentos.buscarDepartamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarDepartamentoPorId(@PathVariable(value = "id") int id){
        return servicioDepartamentos.buscarDepartamentoPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardarDepartamento(@Validated @RequestBody Departamento departamento){
        return servicioDepartamentos.guardarDepartamento(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDepartamento(@PathVariable(value = "id") int id){
        return servicioDepartamentos.eliminarDepartamento(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDepartamento(@Validated @RequestBody Departamento departamento, @PathVariable(value = "id") int id){
        return servicioDepartamentos.actualizarDepartamento(departamento, id);
    }

}
