package com.programacion4tpi.prode.feature.partido.mappers;

import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.equipo.models.Equipo;
import com.programacion4tpi.prode.feature.fecha.models.Fecha;
import com.programacion4tpi.prode.feature.partido.models.Partido;
import org.springframework.stereotype.Component;

@Component
public class PartidoMapper {

    public Partido toEntity(PartidoRequestDto dto, Fecha fecha, Equipo local, Equipo visitante) {
        return Partido.builder()
                .fecha(fecha)
                .equipoLocal(local)
                .equipoVisitante(visitante)
                .fechaHoraInicio(dto.getFechaHoraInicio())
                // El estado 'POR_JUGARSE' se asigna automáticamente por el @Builder.Default en la entidad
                .build();
    }

    public PartidoResponseDto toResponseDto(Partido partido) {
        return PartidoResponseDto.builder()
                .id(partido.getId())
                .fechaNombre(partido.getFecha().getNombre())
                .equipoLocalNombre(partido.getEquipoLocal().getNombre())
                .equipoVisitanteNombre(partido.getEquipoVisitante().getNombre())
                .fechaHoraInicio(partido.getFechaHoraInicio())
                .estado(partido.getEstado().name())
                .build();
    }
}
