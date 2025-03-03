package com.iesbelen.dam.apirest.springapirest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String nombre;

    @Column(name = "num_employees")
    private Integer numEmployees;

    @Column(name = "init_year")
    private Integer year;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(Integer numEmployees) {
        this.numEmployees = numEmployees;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Manufacturer() {
    }

    public Manufacturer(String nombre, Integer numEmployees, Integer year) {
        this.nombre = nombre;
        this.numEmployees = numEmployees;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numEmployees=" + numEmployees +
                ", year=" + year +
                '}';
    }
}
