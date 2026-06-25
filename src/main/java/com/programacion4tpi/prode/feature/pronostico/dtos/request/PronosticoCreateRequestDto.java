package com.programacion4tpi.prode.feature.pronostico.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record PronosticoCreateRequestDto(

        @NotNull(message = "El ID del partido es obligatorio")
        @Positive(message = "El ID del partido debe ser mayor a 0.")
        Long partidoId,

        @NotNull(message = "Campo obligatorio")
        @PositiveOrZero(message = "La cantidad de goles debe ser mayor o igual a 0.")
        Integer golesLocalPredicho,

        @NotNull(message = "Campo obligatorio")
        @PositiveOrZero(message = "La cantidad de goles debe ser mayor o igual a 0.")
        Integer golesVisitantePredicho
) {}
