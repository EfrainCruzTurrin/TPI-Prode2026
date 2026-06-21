package com.programacion4tpi.prode.feature.pronostico.services.interfaces;

import com.programacion4tpi.prode.feature.pronostico.dtos.req.PronosticoCreateRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;

public interface IPronosticoPostService {
    PronosticoResponseDto create(PronosticoCreateRequestDto dto);
}
