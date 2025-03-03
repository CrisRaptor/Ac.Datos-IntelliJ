package com.iesbelen.dam.apirest.springapirest.controller;

import com.iesbelen.dam.apirest.springapirest.model.Manufacturer;
import com.iesbelen.dam.apirest.springapirest.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManufacturerController {

    private ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    /*
    GET http://localhost:8080/api/manufacturers
     */
    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> findAll() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();

        if (manufacturers != null && !manufacturers.isEmpty()) {
            return ResponseEntity.ok(manufacturers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    GET http://localhost:8080/api/manufacturers/year/{year}
     */
    @GetMapping("/manufacturers/year/{year}")
    public ResponseEntity<List<Manufacturer>> findAllByYear(@PathVariable Integer year) {
        List<Manufacturer> manufacturers = manufacturerService.findAllByYear(year);

        if (manufacturers != null && !manufacturers.isEmpty()) {
            return ResponseEntity.ok(manufacturers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    GET http://localhost:8080/api/manufacturers/{id}
     */
    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> findAllById(@PathVariable Long id) {
        return manufacturerService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> create(@RequestBody Manufacturer manufacturer) {

        manufacturerService.save(manufacturer);
        return ResponseEntity.ok(manufacturer);

    }

    @PutMapping("/manufacturers")
    public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);

        return ResponseEntity.ok(manufacturer);
    }

    @DeleteMapping("/manufacturers/{identifier}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable("identifier") Long id) {
        manufacturerService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
