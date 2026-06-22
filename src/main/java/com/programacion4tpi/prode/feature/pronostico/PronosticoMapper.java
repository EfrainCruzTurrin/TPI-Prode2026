package com.programacion4tpi.prode.feature.pronostico;

import com.programacion4tpi.prode.feature.pronostico.dtos.req.PronosticoCreateRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoGetResponseDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import org.springframework.stereotype.Component;

@Component
public class PronosticoMapper {

    public PronosticoResponseDto toDto(Pronostico p) {
        return PronosticoResponseDto.builder()
                .usuarioId(p.getUsuario().getId())
                .partidoId(p.getPartido().getId())
                .golesLocalPredicho(p.getGolesLocalPredicho())
                .golesVisitantePredicho(p.getGolesVisitantePredicho())
                .puntosOtorgados(p.getPuntosOtorgados())
                .fechaCreacion(p.getFechaCreacion())
                .build();
    }

    public PronosticoGetResponseDto toGetResponseDto(Pronostico p) {
        return PronosticoGetResponseDto.builder()
                .id(p.getId())
                .usuarioId(p.getUsuario().getId())
                .username(p.getUsuario().getUsername())
                .partidoId(p.getPartido().getId())
                .golesLocalPredicho(p.getGolesLocalPredicho())
                .golesVisitantePredicho(p.getGolesVisitantePredicho())
                .puntosOtorgados(p.getPuntosOtorgados())
                .fechaCreacion(p.getFechaCreacion())
                .build();
    }

    public Pronostico toEntity(PronosticoCreateRequestDto dto) {
        return Pronostico.builder()
                .golesLocalPredicho(dto.golesLocalPredicho())
                .golesVisitantePredicho(dto.golesVisitantePredicho())
                .build();
    }
}