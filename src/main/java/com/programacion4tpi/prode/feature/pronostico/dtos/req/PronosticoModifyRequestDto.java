package com.programacion4tpi.prode.feature.pronostico.dtos.req;

public record PronosticoModifyRequestDto(
        Long partidoId,
        Integer golesLocalPredicho,
        Integer puntosOtorgados
) {}
