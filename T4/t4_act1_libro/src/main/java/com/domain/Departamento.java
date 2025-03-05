package com.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departamentos")
public class Departamento {
    @Id
    @Column(name = "depno", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 14)
    private String nombre;

    @Column(name = "ubicacion", length = 13)
    private String ubicacion;

    @OneToMany(mappedBy = "depno")
    private Set<Empleado> empleados = new LinkedHashSet<>();

    public String toStringSimple() {
        return "Departamento[ID: " + id +
                ", Nombre: '" + nombre + "', Ubicacion: '" + ubicacion+"]";
    }

    //"Departamento [" + "ID: " + id +", Nombre: '" + nombre + "', Ubicacion: '" + ubicacion + "']"
    @Override
    public String toString() {
        String cadena = "Departamento[ID: " + id +
                ", Nombre: '" + nombre + "', Ubicacion: '" + ubicacion + "', Empleados{\n";
        for (Empleado empleado : empleados){
            cadena += empleado.toStringSimple();
        }
        return cadena + "}]";
    }
}