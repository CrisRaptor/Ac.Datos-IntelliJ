package com.acdat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cuentaDebito")
public class CuentaDebito extends Cuenta implements Serializable {
    @Id
    @Column
    private String titular;

    @Column
    private double balance;

    @Column
    private double tipoInteres;

    @Column
    private double cargoPorDescubierto;

    public CuentaDebito() {
    }
}