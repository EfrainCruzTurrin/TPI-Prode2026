package com.programacion4tpi.prode.feature.partido.dtos.request;

import lombok.Data;
import java.time.Instant;

@Data
public class PartidoUpdateRequestDto {
    private Instant fechaHoraInicio;
    private String estado; // POR_JUGARSE, EN_JUEGO, FINALIZADO
    private Integer golesLocal;
    private Integer golesVisitante;
    private String resultado; // LOCAL, EMPATE, VISITANTE
}
