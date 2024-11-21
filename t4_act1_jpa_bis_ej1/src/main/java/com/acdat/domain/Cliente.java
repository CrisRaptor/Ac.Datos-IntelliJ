package com.acdat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion idDireccion;

    @ManyToMany
    @JoinTable(name = "clientes_talleres", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_taller"))
    private Set<Taller> talleres = new HashSet<>();

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", idDireccion=" + idDireccion +
                ", talleres=" + talleres +
                '}';
    }
}