package com.programacion4tpi.prode.feature.partido.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistorialPartidoResponseDto {
    private Long idPartido;
    private String nombreEquipoLocal;
    private String nombreEquipoVisitante;
    private Integer golesLocal;
    private Integer golesVisitante;
    private String nombreFecha; // Ej: "Fecha 1", "8vos de Final"
    private String estadoPartido; // Para asegurarnos de que esté FINALIZADO
}