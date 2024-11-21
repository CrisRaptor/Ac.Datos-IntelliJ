package com.acdat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "talleres")
public class Taller {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion idDireccion;

    @ManyToMany(mappedBy = "talleres")
    private Set<Cliente> clientes = new HashSet<>();

    @Override
    public String toString() {
        return "Taller{" +
                "id=" + id +
                ", idDireccion=" + idDireccion +
                ", clientes=" + clientes +
                '}';
    }
}