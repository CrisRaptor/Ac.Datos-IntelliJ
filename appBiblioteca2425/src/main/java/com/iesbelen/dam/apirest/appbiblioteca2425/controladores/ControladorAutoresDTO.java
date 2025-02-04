package com.iesbelen.dam.apirest.appbiblioteca2425.controladores;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dto.AutorDTO;
import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Autor;
import com.iesbelen.dam.apirest.appbiblioteca2425.servicios.ServicioAutores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/autoresdto")
public class ControladorAutoresDTO {

    @Autowired
    private ServicioAutores servicioAutores;

    @PostMapping
    public ResponseEntity<?> agregarAutores(@RequestBody List<AutorDTO> autoresDTO) {
        if (autoresDTO.isEmpty() || autoresDTO.size() > 3) {
            return ResponseEntity.badRequest().body(autoresDTO);
        } else {
            List<AutorDTO> autorDTOs = new ArrayList<>();
            for (AutorDTO autor : autoresDTO) {
                ResponseEntity<?> response = servicioAutores.guardarAutorDTO(autor);
                if (response.getStatusCode().is2xxSuccessful()) {
                    autorDTOs.add((AutorDTO) response.getBody());
                } else {
                    return ResponseEntity.badRequest().body(response.getBody());
                }
            }
            return ResponseEntity.ok().body(autorDTOs);
        }
    }
}
