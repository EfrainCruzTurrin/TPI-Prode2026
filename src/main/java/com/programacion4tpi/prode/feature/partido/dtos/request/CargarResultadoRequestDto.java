package com.programacion4tpi.prode.feature.partido.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CargarResultadoRequestDto {

    @NotNull(message = "Los goles del equipo local son obligatorios.")
    @Min(value = 0, message = "Los goles no pueden ser negativos.")
    private Integer golesLocal;

    @NotNull(message = "Los goles del equipo visitante son obligatorios.")
    @Min(value = 0, message = "Los goles no pueden ser negativos.")
    private Integer golesVisitante;
}