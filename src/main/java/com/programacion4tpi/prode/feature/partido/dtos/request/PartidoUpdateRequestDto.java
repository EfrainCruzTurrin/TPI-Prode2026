package com.programacion4tpi.prode.feature.partido.dtos.request;

import lombok.Data;
import java.time.Instant;

@Data
public class PartidoUpdateRequestDto {
    private Long fechaId;
    private Long equipoLocalId;
    private Long equipoVisitanteId;
    private Instant fechaHoraInicio;
    private String estado;
    private Integer golesLocal;
    private Integer golesVisitante;
    private String resultado;
}