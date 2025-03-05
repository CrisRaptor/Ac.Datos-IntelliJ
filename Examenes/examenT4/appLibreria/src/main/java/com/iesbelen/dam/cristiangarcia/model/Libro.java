package com.iesbelen.dam.cristiangarcia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "libro_id")
    private Long id;

    @Column(name="titulo")
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Autor autor;

    public Libro() {
    }

    public Libro(String titulo, Long id) {
        this.titulo = titulo;
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autor=[" + autor.toStringSimple() +
                "], id=" + id +
                ", titulo='" + titulo + "'" +
                '}';
    }

    public String toStringSimple() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + "'" +
                '}';
    }
}
