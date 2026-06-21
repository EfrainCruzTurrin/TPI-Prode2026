package com.programacion4tpi.prode.feature.pronostico.services.interfaces;

import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import java.util.List;

public interface IPronosticoGetService {
    List<PronosticoResponseDto> listarPronosticos();
}
