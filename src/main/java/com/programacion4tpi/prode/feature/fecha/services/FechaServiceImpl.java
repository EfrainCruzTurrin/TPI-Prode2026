package com.programacion4tpi.prode.feature.fecha.services;

import com.programacion4tpi.prode.exceptions.fechas.FechaDuplicadaException;
import com.programacion4tpi.prode.feature.fecha.dtos.request.FechaRequestDto;
import com.programacion4tpi.prode.feature.fecha.dtos.response.FechaResponseDto;
import com.programacion4tpi.prode.feature.fecha.models.enums.EstadoFecha;
import com.programacion4tpi.prode.feature.fecha.models.Fecha;
import com.programacion4tpi.prode.feature.fecha.repository.FechaRepository;
import com.programacion4tpi.prode.feature.fecha.services.interfaces.IFechaService;
import com.programacion4tpi.prode.feature.partido.models.enums.EstadoPartido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FechaServiceImpl implements IFechaService {

    private final FechaRepository fechaRepository;

    @Override
    @Transactional
    public FechaResponseDto crear(FechaRequestDto dto) {

        if (fechaRepository.existsByNombre(dto.getNombre())) {
            throw new FechaDuplicadaException(
                    "Ya existe una fecha con ese nombre"
            );
        }

        Fecha fecha = Fecha.builder()
                .nombre(dto.getNombre())
                .estado(EstadoFecha.PROGRAMADA)
                .build();

        Fecha saved = fechaRepository.save(fecha);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FechaResponseDto> listar() {

        return fechaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public void actualizarEstadoFecha(Fecha fecha, List<EstadoPartido> estados) {

        EstadoFecha nuevoEstado;

        if (estados.isEmpty()) {
            nuevoEstado = EstadoFecha.PROGRAMADA;
        } else if (estados.contains(EstadoPartido.EN_JUEGO)) {
            nuevoEstado = EstadoFecha.EN_JUEGO;
        } else if (estados.stream().allMatch(EstadoPartido.FINALIZADO::equals)) {
            nuevoEstado = EstadoFecha.FINALIZADA;
        } else {
            nuevoEstado = EstadoFecha.PROGRAMADA;
        }

        fecha.setEstado(nuevoEstado);
        fechaRepository.save(fecha);
    }

    private FechaResponseDto mapToResponse(Fecha fecha) {

        return new FechaResponseDto(
                fecha.getId(),
                fecha.getNombre(),
                fecha.getEstado()
        );
    }
}