package com.iesbelen.dam.apirest.apirest1.controladores;

import com.iesbelen.dam.apirest.apirest1.modelo.dao.IDepartamentosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.dao.IEmpleadosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Departamento;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class ControladorDepartamentos {

    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping
    public List<Departamento> buscarDepartamentos(){
        return (List<Departamento>) departamentosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarDepartamentoPorId(@PathVariable(value = "id") int id){
        Optional<Departamento> departamento = departamentosDAO.findById(id);
        if(departamento.isPresent()) {
            return ResponseEntity.ok().body(departamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Departamento guardarDepartamento(@Validated @RequestBody Departamento departamento){
        return departamentosDAO.save(departamento);
    }
}
