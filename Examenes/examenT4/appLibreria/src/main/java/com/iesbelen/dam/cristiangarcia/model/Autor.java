package com.iesbelen.dam.cristiangarcia.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="autor_id")
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Libro> libros;

    public Autor() {}

    public Autor(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String cadena = "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                ", libros=[";
        if (libros != null) {
            for (Libro libro : libros) {
                cadena += "\n\t" + libro.toStringSimple();
            }
        }
        return cadena+"]}";
    }

    public String toStringSimple() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                '}';
    }
}
