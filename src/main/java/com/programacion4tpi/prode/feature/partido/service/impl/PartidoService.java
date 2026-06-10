package com.programacion4tpi.prode.feature.partido.service.impl;

import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoUpdateRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;

public interface PartidoService {
    PartidoResponseDto create(PartidoRequestDto dto);
    PartidoResponseDto update(Long id, PartidoUpdateRequestDto dto);
}
