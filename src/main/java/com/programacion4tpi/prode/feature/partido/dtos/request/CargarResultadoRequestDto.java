package com.programacion4tpi.prode.feature.partido.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CargarResultadoRequestDto {

    @NotNull(message = "Los goles del equipo local son obligatorios.")
    @PositiveOrZero(message = "La cantidad de goles debe ser mayor o igual a 0.")
    private Integer golesLocal;

    @NotNull(message = "Los goles del equipo visitante son obligatorios.")
    @PositiveOrZero(message = "La cantidad de goles debe ser mayor o igual a 0.")
    private Integer golesVisitante;
}