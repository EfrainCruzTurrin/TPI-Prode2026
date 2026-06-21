package com.programacion4tpi.prode.feature.pronostico.dtos.req;


public record PronosticoCreateRequestDto(
        Long partidoId,
        Integer golesLocalPredicho,
        Integer golesVisitantePredicho
) {}
