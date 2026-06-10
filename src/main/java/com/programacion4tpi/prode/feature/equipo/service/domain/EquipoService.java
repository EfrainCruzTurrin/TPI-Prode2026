package com.programacion4tpi.prode.feature.equipo.service.domain;

import com.programacion4tpi.prode.feature.equipo.dtos.request.EquipoRequestDto;
import com.programacion4tpi.prode.feature.equipo.dtos.response.EquipoResponseDto;

import java.util.List;

public interface EquipoService {

    EquipoResponseDto crearEquipo(EquipoRequestDto requestDto);

    List<EquipoResponseDto> listarEquipos();

    EquipoResponseDto obtenerEquipoPorId(Long id);

    void eliminarEquipo(Long id);
}