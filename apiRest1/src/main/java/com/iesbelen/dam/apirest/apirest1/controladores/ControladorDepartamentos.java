package com.iesbelen.dam.apirest.apirest1.controladores;

import com.iesbelen.dam.apirest.apirest1.modelo.dao.IDepartamentosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@RepositoryRestResource(collectionResourceRel = "departamentos", path = "departamentos")
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
    public ResponseEntity<?> guardarDepartamento(@Validated @RequestBody Departamento departamento){
        if (!departamentosDAO.existsById(departamento.getId())) {
            return ResponseEntity.ok().body(departamentosDAO.save(departamento));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDepartamento(@PathVariable(value = "id") int id){
        if (departamentosDAO.existsById(id)) {
            departamentosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDepartamento(@Validated @RequestBody Departamento departamento, @PathVariable(value = "id") int id){
        if(departamentosDAO.existsById(id)) {
            departamentosDAO.save(departamento);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
