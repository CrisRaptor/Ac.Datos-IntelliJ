package com.acdat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "concesionario")
public class Vehiculo {
    @Id
    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    private String color;

    @Column
    private Date fecha;

    @Column
    private long numeroKilometros;

    @ManyToOne
    private Concesionario concesionario;

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", fecha=" + fecha +
                ", numeroKilometros=" + numeroKilometros +
                '}';
    }
}
