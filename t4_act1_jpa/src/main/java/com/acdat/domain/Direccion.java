package  com.acdat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @OneToOne(mappedBy = "idDireccion")
    private Cliente cliente = new Cliente();

    @OneToOne(mappedBy = "idDireccion")
    private Taller taller = new Taller();

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cliente=" + cliente +
                ", taller=" + taller +
                '}';
    }
}