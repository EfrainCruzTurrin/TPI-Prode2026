package com.programacion4tpi.prode.feature.fecha.dtos.request;

import jakarta.validation.constraints.NotBlank;

public class FechaRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}