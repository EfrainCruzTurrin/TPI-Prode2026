package com.programacion4tpi.prode.feature.fecha.services.interfaces;

import com.programacion4tpi.prode.feature.fecha.dtos.request.FechaRequestDto;
import com.programacion4tpi.prode.feature.fecha.dtos.response.FechaResponseDto;
import com.programacion4tpi.prode.feature.fecha.models.Fecha;
import com.programacion4tpi.prode.feature.partido.models.enums.EstadoPartido;
import java.util.List;


public interface IFechaService {

    FechaResponseDto crear(FechaRequestDto dto);

    List<FechaResponseDto> listar();

    void actualizarEstadoFecha(
            Fecha fecha,
            List<EstadoPartido> estados
    );
}