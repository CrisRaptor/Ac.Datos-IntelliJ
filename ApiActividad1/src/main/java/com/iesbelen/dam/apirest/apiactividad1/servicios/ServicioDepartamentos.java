package com.iesbelen.dam.apirest.apiactividad1.servicios;

import com.iesbelen.dam.apirest.apiactividad1.modelo.dao.IDepartamentosDAO;
import com.iesbelen.dam.apirest.apiactividad1.modelo.entidades.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDepartamentos {
    @Autowired
    IDepartamentosDAO departamentosDAO;

    public List<Departamento> buscarDepartamentos(){
        return (List<Departamento>) departamentosDAO.findAll();
    }

    public ResponseEntity<Departamento> buscarDepartamentoPorId(int id){
        Optional<Departamento> departamento = departamentosDAO.findById(id);
        if(departamento.isPresent()) {
            return ResponseEntity.ok().body(departamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> guardarDepartamento(Departamento departamento){
        if (!departamentosDAO.existsById(departamento.getId())) {
            return ResponseEntity.ok().body(departamentosDAO.save(departamento));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> eliminarDepartamento(int id){
        if (departamentosDAO.existsById(id)) {
            departamentosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> actualizarDepartamento(Departamento departamento, int id){
        if(departamentosDAO.existsById(id)) {
            departamentosDAO.save(departamento);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
