package com.programacion4tpi.prode.feature.pronostico.services.impl.interfaces;

import com.programacion4tpi.prode.feature.pronostico.dtos.response.PronosticoResponseDto;
import java.util.List;

public interface IPronosticoGetService {
    List<PronosticoResponseDto> listarPronosticos();
    List<PronosticoResponseDto> listarMisPronosticos();
}
