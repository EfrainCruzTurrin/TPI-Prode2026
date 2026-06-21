package com.programacion4tpi.prode.feature.pronostico.services.interfaces;

import com.programacion4tpi.prode.feature.pronostico.dtos.req.PronosticoModifyRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;

public interface IPronosticoModifyService {
    PronosticoResponseDto actualizarPronostico(PronosticoModifyRequestDto dto);
}
