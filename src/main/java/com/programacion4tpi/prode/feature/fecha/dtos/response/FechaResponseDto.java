package com.programacion4tpi.prode.feature.fecha.dtos.response;

import com.programacion4tpi.prode.feature.fecha.models.EstadoFecha;

public class FechaResponseDto {

    private Long id;
    private String nombre;
    private EstadoFecha estado;

    public FechaResponseDto() {
    }

    public FechaResponseDto(Long id, String nombre, EstadoFecha estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoFecha getEstado() {
        return estado;
    }

    public void setEstado(EstadoFecha estado) {
        this.estado = estado;
    }
}
