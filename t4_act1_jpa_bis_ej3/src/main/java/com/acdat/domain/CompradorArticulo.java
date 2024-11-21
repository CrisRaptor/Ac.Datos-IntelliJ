package com.acdat.domain;

import jakarta.persistence.*;

@Entity
@IdClass(CompradorArticuloId.class)
public class CompradorArticulo {
    @Id
    @ManyToOne
    @JoinColumn(name = "articulo_id", insertable = false, updatable = false)
    private Articulo articulo;

    @Id
    @ManyToOne
    @JoinColumn(name = "comprador_id", insertable = false, updatable = false)
    private Comprador comprador;
}
