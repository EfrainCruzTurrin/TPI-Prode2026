package com.programacion4tpi.prode.feature.partido.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.Instant;

@Data
public class PartidoRequestDto {

    @NotNull(message = "El ID de la fecha es obligatorio")
    private Long fechaId;

    @NotNull(message = "El ID del equipo local es obligatorio")
    private Long equipoLocalId;

    @NotNull(message = "El ID del equipo visitante es obligatorio")
    private Long equipoVisitanteId;

    @NotNull(message = "La fecha y hora de inicio es obligatoria")
    private Instant fechaHoraInicio;
}