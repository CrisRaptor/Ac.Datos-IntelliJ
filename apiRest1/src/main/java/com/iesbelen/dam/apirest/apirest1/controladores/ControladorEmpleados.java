package com.iesbelen.dam.apirest.apirest1.controladores;

import com.iesbelen.dam.apirest.apirest1.modelo.dao.IDepartamentosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.dao.IEmpleadosDAO;
import com.iesbelen.dam.apirest.apirest1.modelo.dto.EmpleadoDTO;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Departamento;
import com.iesbelen.dam.apirest.apirest1.modelo.entidades.Empleado;

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
    IEmpleadosDAO empleadosDAO;
    @Autowired
    IDepartamentosDAO departamentosDAO;

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

    //DTO
    @GetMapping("dto/{id}")
    public ResponseEntity<EmpleadoDTO> buscarEmpleadosDTOPorId(@PathVariable(value = "id") int id){
        Optional<Empleado> empleado = empleadosDAO.findById(id);

        if (empleado.isPresent()) {
//            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
//            empleadoDTO.setEmpno(empleado.get().getId());
//            empleadoDTO.setNombre(empleado.get().getNombre());
//            empleadoDTO.setPuesto(empleado.get().getPuesto());
//            empleadoDTO.setDepartamento(empleado.get().getDepno().getId());
//            empleadoDTO.setDepartamentoNombre(empleado.get().getDepno().getNombre());
//            empleadoDTO.setDepartamentoUbicacion(empleado.get().getDepno().getUbicacion());
            Optional<Departamento> departamento = departamentosDAO.findById(empleado.get().getDepno().getId());
            ModelMapper mapper = new ModelMapper();
            EmpleadoDTO empleadoDTO = mapper.map(empleado.get(), EmpleadoDTO.class);
            mapper.map(departamento.get(), empleadoDTO);

            return ResponseEntity.ok().body(empleadoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
