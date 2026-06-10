package com.programacion4tpi.prode.feature.partido.service.impl;

import com.programacion4tpi.prode.feature.equipo.models.Equipo;
import com.programacion4tpi.prode.feature.equipo.repository.EquipoRepository;
import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoUpdateRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.mappers.PartidoMapper;
import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.partido.repository.PartidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final FechaRepository fechaRepository;
    private final EquipoRepository equipoRepository;
    private final PartidoMapper partidoMapper;

    @Override
    @Transactional
    public PartidoResponseDto create(PartidoRequestDto dto) {
        if (dto.getEquipoLocalId().equals(dto.getEquipoVisitanteId())) {
            throw new IllegalArgumentException("El equipo local y el equipo visitante no pueden ser el mismo.");
        }

        Fecha fecha = fechaRepository.findById(dto.getFechaId())
                .orElseThrow(() -> new RuntimeException("Fecha no encontrada"));

        Equipo local = equipoRepository.findById(dto.getEquipoLocalId())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));

        Equipo visitante = equipoRepository.findById(dto.getEquipoVisitanteId())
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));

        Partido partido = partidoMapper.toEntity(dto, fecha, local, visitante);
        return partidoMapper.toResponseDto(partidoRepository.save(partido));
    }

    @Override
    @Transactional
    public PartidoResponseDto update(Long id, PartidoUpdateRequestDto dto) {
        Partido existing = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        if (dto.getFechaHoraInicio() != null) {
            existing.setFechaHoraInicio(dto.getFechaHoraInicio());
        }
        if (dto.getEstado() != null) {
            existing.setEstado(Partido.EstadoPartido.valueOf(dto.getEstado()));
        }
        if (dto.getGolesLocal() != null) {
            existing.setGolesLocal(dto.getGolesLocal());
        }
        if (dto.getGolesVisitante() != null) {
            existing.setGolesVisitante(dto.getGolesVisitante());
        }
        if (dto.getResultado() != null) {
            existing.setResultado(Partido.ResultadoPartido.valueOf(dto.getResultado()));
        }

        return partidoMapper.toResponseDto(partidoRepository.save(existing));
    }
}