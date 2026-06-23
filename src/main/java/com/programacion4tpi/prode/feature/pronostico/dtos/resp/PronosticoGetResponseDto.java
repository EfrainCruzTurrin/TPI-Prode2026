package com.programacion4tpi.prode.feature.pronostico.dtos.resp;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record PronosticoGetResponseDto(
        Long id,
        Long usuarioId,
        String username,
        Long partidoId,
        Integer golesLocalPredicho,
        Integer golesVisitantePredicho,
        Integer puntosOtorgados,
        LocalDateTime fechaCreacion
) {}