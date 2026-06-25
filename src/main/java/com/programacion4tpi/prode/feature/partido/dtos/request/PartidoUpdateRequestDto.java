package com.programacion4tpi.prode.feature.partido.dtos.request;

import com.programacion4tpi.prode.feature.partido.models.enums.EstadoPartido;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidoUpdateRequestDto {

    @Positive(message = "El ID de la fecha debe ser mayor a 0.")
    private Long fechaId;

    @Positive(message = "El ID del equipo debe ser mayor a 0.")
    private Long equipoLocalId;

    @Positive(message = "El ID del equipo debe ser mayor a 0.")
    private Long equipoVisitanteId;

    private Instant fechaHoraInicio;

    private EstadoPartido estado;

    @PositiveOrZero(message = "La cantidad de goles debe ser mayor o igual a 0.")
    private Integer golesLocal;

    @PositiveOrZero(message = "La cantidad de goles debe ser mayor o igual a 0.")
    private Integer golesVisitante;
}