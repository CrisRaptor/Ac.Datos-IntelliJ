package com.acdat.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue
    private long id;

    @Column
    private String titular;

    @Column
    private double balance;

    @Column
    private double tipoInteres;
}
