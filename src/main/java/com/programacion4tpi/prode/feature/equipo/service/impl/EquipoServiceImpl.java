package com.programacion4tpi.prode.feature.equipo.service.impl;

import com.programacion4tpi.prode.exceptions.ConflictException;
import com.programacion4tpi.prode.exceptions.ResourceNotFoundException;
import com.programacion4tpi.prode.feature.equipo.dtos.request.EquipoRequestDto;
import com.programacion4tpi.prode.feature.equipo.dtos.response.EquipoResponseDto;
import com.programacion4tpi.prode.feature.equipo.models.Equipo;
import com.programacion4tpi.prode.feature.equipo.repository.EquipoRepository;
import com.programacion4tpi.prode.feature.equipo.service.domain.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

    @Override
    @Transactional
    public EquipoResponseDto crearEquipo(EquipoRequestDto requestDto) {
        if (equipoRepository.existsByNombreIgnoreCase(requestDto.getNombre())) {
            throw new ConflictException(
                    "Ya existe un equipo con el nombre: '" + requestDto.getNombre() + "'."
            );
        }

        Equipo nuevoEquipo = Equipo.builder()
                .nombre(requestDto.getNombre())
                .escudoUrl(requestDto.getEscudoUrl())
                .build();

        return mapToResponseDto(equipoRepository.save(nuevoEquipo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipoResponseDto> listarEquipos() {
        return equipoRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EquipoResponseDto obtenerEquipoPorId(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Equipo no encontrado con ID: " + id
                ));
        return mapToResponseDto(equipo);
    }

    @Override
    @Transactional
    public void eliminarEquipo(Long id) {
        // Verificar que el equipo existe antes de intentar borrar
        if (!equipoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipo no encontrado con ID: " + id);
        }

        try {
            equipoRepository.deleteById(id);
            // Forzar el flush para que la BD lance la excepción de FK en esta transacción
            equipoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            // AC: Impedir eliminación si viola integridad referencial (partidos asociados)
            throw new ConflictException(
                    "No se puede eliminar el equipo con ID " + id +
                            " porque tiene partidos asociados."
            );
        }
    }

    // --- Método privado de mapeo ---
    private EquipoResponseDto mapToResponseDto(Equipo equipo) {
        return EquipoResponseDto.builder()
                .id(equipo.getId())
                .nombre(equipo.getNombre())
                .escudoUrl(equipo.getEscudoUrl())
                .build();
    }
}