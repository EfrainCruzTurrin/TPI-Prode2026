package com.programacion4tpi.prode.feature.partido.service.impl;

import com.programacion4tpi.prode.exceptions.global.BadRequestException;
import com.programacion4tpi.prode.exceptions.global.ResourceNotFoundException;
import com.programacion4tpi.prode.feature.equipo.models.Equipo;
import com.programacion4tpi.prode.feature.equipo.repository.EquipoRepository;
import com.programacion4tpi.prode.feature.fecha.models.Fecha;
import com.programacion4tpi.prode.feature.fecha.repository.FechaRepository;
import com.programacion4tpi.prode.feature.partido.dtos.request.CargarResultadoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoUpdateRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.mappers.PartidoMapper;
import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.partido.repository.PartidoRepository;
import com.programacion4tpi.prode.feature.pronostico.service.domain.PuntuacionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final FechaRepository fechaRepository;
    private final EquipoRepository equipoRepository;
    private final PartidoMapper partidoMapper;
    private final PuntuacionService puntuacionService;

    @Override
    @Transactional
    public PartidoResponseDto create(PartidoRequestDto dto) {
        validarEquiposDistintos(dto.getEquipoLocalId(), dto.getEquipoVisitanteId());

        Fecha fecha = fechaRepository.findById(dto.getFechaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fecha no encontrada con id: " + dto.getFechaId()));

        Equipo local = equipoRepository.findById(dto.getEquipoLocalId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Equipo local no encontrado con id: " + dto.getEquipoLocalId()));

        Equipo visitante = equipoRepository.findById(dto.getEquipoVisitanteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Equipo visitante no encontrado con id: " + dto.getEquipoVisitanteId()));

        Partido partido = partidoMapper.toEntity(dto, fecha, local, visitante);
        return partidoMapper.toResponseDto(partidoRepository.save(partido));
    }

    @Override
    @Transactional
    public PartidoResponseDto update(Long id, PartidoUpdateRequestDto dto) {
        Partido existing = partidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id: " + id));

        if (dto.getFechaId() != null) {
            Fecha fecha = fechaRepository.findById(dto.getFechaId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Fecha no encontrada con id: " + dto.getFechaId()));
            existing.setFecha(fecha);
        }

        // Determinamos cuáles serían los IDs finales (nuevos o los actuales) para validar
        Long localIdFinal = dto.getEquipoLocalId() != null
                ? dto.getEquipoLocalId() : existing.getEquipoLocal().getId();
        Long visitanteIdFinal = dto.getEquipoVisitanteId() != null
                ? dto.getEquipoVisitanteId() : existing.getEquipoVisitante().getId();

        validarEquiposDistintos(localIdFinal, visitanteIdFinal);

        if (dto.getEquipoLocalId() != null) {
            Equipo local = equipoRepository.findById(dto.getEquipoLocalId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Equipo local no encontrado con id: " + dto.getEquipoLocalId()));
            existing.setEquipoLocal(local);
        }

        if (dto.getEquipoVisitanteId() != null) {
            Equipo visitante = equipoRepository.findById(dto.getEquipoVisitanteId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Equipo visitante no encontrado con id: " + dto.getEquipoVisitanteId()));
            existing.setEquipoVisitante(visitante);
        }

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

    private void validarEquiposDistintos(Long equipoLocalId, Long equipoVisitanteId) {
        if (equipoLocalId.equals(equipoVisitanteId)) {
            throw new BadRequestException(
                    "El equipo local y el equipo visitante no pueden ser el mismo.");
        }
    }

    @Override
    public List<PartidoResponseDto> listar() {
        return partidoRepository.findAll().stream()
                .map(partidoMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PartidoResponseDto cargarResultado(Long id, CargarResultadoRequestDto dto) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id: " + id));

        // AC: el partido debe estar EN_JUEGO
        if (partido.getEstado() != Partido.EstadoPartido.EN_JUEGO) {
            throw new BadRequestException(
                    "Solo se puede cargar resultado de un partido en estado EN_JUEGO. Estado actual: "
                            + partido.getEstado());
        }

        partido.setGolesLocal(dto.getGolesLocal());
        partido.setGolesVisitante(dto.getGolesVisitante());

        // Derivar resultado automáticamente
        Partido.ResultadoPartido resultado;
        if (dto.getGolesLocal() > dto.getGolesVisitante())      resultado = Partido.ResultadoPartido.LOCAL;
        else if (dto.getGolesLocal() < dto.getGolesVisitante()) resultado = Partido.ResultadoPartido.VISITANTE;
        else                                                     resultado = Partido.ResultadoPartido.EMPATE;

        partido.setResultado(resultado);
        partido.setEstado(Partido.EstadoPartido.FINALIZADO); // AC: estado → FINALIZADO

        Partido saved = partidoRepository.save(partido);

        // Disparar el motor de puntuación
        puntuacionService.calcularYAsignarPuntos(saved);

        return partidoMapper.toResponseDto(saved);
    }
}