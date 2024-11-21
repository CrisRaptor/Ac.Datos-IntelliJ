package com.acdat.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Nombre", length = 48)
    private String name;

    @Column(name = "FechaCumpleanyos")
    @CreationTimestamp
    private Date birthDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
