package com.iesbelen.dam.apirest.springapirest.repository;

import com.iesbelen.dam.apirest.springapirest.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> findAllByYear(Integer year);
}
