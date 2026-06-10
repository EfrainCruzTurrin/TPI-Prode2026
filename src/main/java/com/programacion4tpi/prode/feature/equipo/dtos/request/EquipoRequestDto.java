package com.programacion4tpi.prode.feature.equipo.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipoRequestDto {

    @NotBlank(message = "El nombre del equipo no puede estar vacío.")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres.")
    private String nombre;

    @Size(max = 50, message = "El país no puede superar los 50 caracteres.")
    private String pais;  // campo nuevo, opcional

    @Size(max = 150, message = "La URL del escudo no puede superar los 150 caracteres.")
    private String escudoUrl;
}