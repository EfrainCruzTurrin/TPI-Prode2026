package com.programacion4tpi.prode.feature.pronostico.mappers;

import com.programacion4tpi.prode.feature.pronostico.dtos.request.PronosticoCreateRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.response.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import org.springframework.stereotype.Component;

@Component
public class PronosticoMapper {

    public PronosticoResponseDto toDto(Pronostico p) {
        return PronosticoResponseDto.builder()
                .id(p.getId())
                .usuarioId(p.getUsuario().getId())
                .partidoId(p.getPartido().getId())
                .golesLocalPredicho(p.getGolesLocalPredicho())
                .golesVisitantePredicho(p.getGolesVisitantePredicho())
                .fechaCreacion(p.getFechaCreacion())
                .build();
    }

    // como se busca por ID...
    public Pronostico toEntity(PronosticoCreateRequestDto dto) {
        return Pronostico.builder()
                .golesLocalPredicho(dto.golesLocalPredicho())
                .golesVisitantePredicho(dto.golesVisitantePredicho())
                .build();
    }
}
