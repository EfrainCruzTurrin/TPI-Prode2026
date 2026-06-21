package com.programacion4tpi.prode.feature.pronostico;

import com.programacion4tpi.prode.feature.pronostico.dtos.req.PronosticoCreateRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import org.springframework.stereotype.Component;

@Component
public class PronosticoMapper {

    public PronosticoResponseDto toDto(Pronostico p) {
        return PronosticoResponseDto.builder()
                .usuarioId(p.getId())
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
