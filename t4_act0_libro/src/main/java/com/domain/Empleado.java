package com.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @Column(name = "empno", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 10)
    private String nombre;

    @Column(name = "puesto", length = 15)
    private String puesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depno")
    private Departamento depno;

    public String toStringSimple() {
        return "   Empleado[ID: " + id +
                ", Nombre: " + nombre + "', Puesto: '" + puesto + "']\n";
    }

    @Override
    public String toString() {
        return "Empleado[ID: " + id +
                ", Nombre: '" + nombre + "', Ubicacion: '" + puesto + "', Departamento: " + depno + "}]";
    }
}