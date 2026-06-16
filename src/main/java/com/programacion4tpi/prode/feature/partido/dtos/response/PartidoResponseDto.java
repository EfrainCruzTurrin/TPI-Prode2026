package com.programacion4tpi.prode.feature.partido.dtos.response;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class PartidoResponseDto {
    private Long id;
    private String fechaNombre;
    private String equipoLocalNombre;
    private String equipoVisitanteNombre;
    private Instant fechaHoraInicio;
    private String estado;
}
