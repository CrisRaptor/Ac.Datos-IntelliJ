package com.iesbelen.dam.apirest.apirestfutbol2425.modelos.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class PartidoDTO {
    private String nombreEquipoLocal, escudoEquipoLocal;
    private String nombreEquipoVisitante, escudoEquipoVisitante;
    private String fecha;
    private String hora;
    private Integer golesEquipoLocal, golesEquipoVisitante;

    public String getNombreEquipoLocal() {
        return nombreEquipoLocal;
    }

    public String getEscudoEquipoLocal() {
        return escudoEquipoLocal;
    }

    public String getNombreEquipoVisitante() {
        return nombreEquipoVisitante;
    }

    public String getEscudoEquipoVisitante() {
        return escudoEquipoVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public String  getHora() {
        return hora;
    }

    public Integer getGolesEquipoLocal() {
        return golesEquipoLocal;
    }

    public Integer getGolesEquipoVisitante() {
        return golesEquipoVisitante;
    }

    public void setNombreEquipoLocal(String nombreEquipoLocal) {
        this.nombreEquipoLocal = nombreEquipoLocal;
    }

    public void setEscudoEquipoLocal(String escudoEquipoLocal) {
        this.escudoEquipoLocal = escudoEquipoLocal;
    }

    public void setNombreEquipoVisitante(String nombreEquipoVisitante) {
        this.nombreEquipoVisitante = nombreEquipoVisitante;
    }

    public void setEscudoEquipoVisitante(String escudoEquipoVisitante) {
        this.escudoEquipoVisitante = escudoEquipoVisitante;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setGolesEquipoLocal(Integer golesEquipoLocal) {
        this.golesEquipoLocal = golesEquipoLocal;
    }

    public void setGolesEquipoVisitante(Integer golesEquipoVisitante) {
        this.golesEquipoVisitante = golesEquipoVisitante;
    }
}
