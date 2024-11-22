package com.acdat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "concesionario")
public class Concesionario {
    @Id
    @Column(name = "nombreComercial", nullable = false)
    private String nombreComercial;

    @Column(name = "nombreEmpresarial", nullable = false)
    private String nombreEmpresarial;

    @Column(name = "direccion", nullable = false)
    private String direccionConcesionario;

    @Column(name = "numTrabajadores")
    private int numTrabajadores;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "concesionario", cascade = CascadeType.REMOVE)
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public void addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.setConcesionario(this);
    }
    public void removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.setConcesionario(null);
    }

    @Override
    public String toString() {
        return "Concesionario{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nombreEmpresarial='" + nombreEmpresarial + '\'' +
                ", direccionConcesionario='" + direccionConcesionario + '\'' +
                ", numTrabajadores=" + numTrabajadores +
                ", email='" + email + '\'' +
                '}';
    }
}
