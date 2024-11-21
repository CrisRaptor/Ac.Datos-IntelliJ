package com.acdat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "articulo")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int idArticulo;

    @Column
    private String descripcion;

    @Column
    private long precioVenta;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompradorArticulo> unidadesVendidas;

}
