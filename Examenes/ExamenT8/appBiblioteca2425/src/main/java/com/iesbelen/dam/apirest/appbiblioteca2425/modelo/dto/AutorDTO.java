package com.iesbelen.dam.apirest.appbiblioteca2425.modelo.dto;

import com.iesbelen.dam.apirest.appbiblioteca2425.modelo.entidades.Libro;

import java.util.List;

public class AutorDTO {
    int id;
    String nombreAutor,pais;

    public AutorDTO() {
    }

    public int getId() {
        return id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public String getPais() {
        return pais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
