package com.acdat.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class CompradorArticuloId implements Serializable {
    @Getter @Setter
    private Articulo articulo;
    @Getter @Setter
    private Comprador comprador;

    public CompradorArticuloId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompradorArticuloId that = (CompradorArticuloId) o;
        return Objects.equals(articulo, that.articulo) && Objects.equals(comprador, that.comprador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articulo, comprador);
    }
}
