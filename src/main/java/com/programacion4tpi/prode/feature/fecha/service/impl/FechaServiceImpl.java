package com.programacion4tpi.prode.feature.fecha.service.impl;

import com.programacion4tpi.prode.exceptions.fechas.FechaDuplicadaException;
import com.programacion4tpi.prode.feature.fecha.dtos.request.FechaRequestDto;
import com.programacion4tpi.prode.feature.fecha.dtos.response.FechaResponseDto;
import com.programacion4tpi.prode.feature.fecha.models.EstadoFecha;
import com.programacion4tpi.prode.feature.fecha.models.EstadoPartido;
import com.programacion4tpi.prode.feature.fecha.models.Fecha;
import com.programacion4tpi.prode.feature.fecha.repository.FechaRepository;
import com.programacion4tpi.prode.feature.fecha.service.domain.FechaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FechaServiceImpl implements FechaService {

    private final FechaRepository fechaRepository;

    public FechaServiceImpl(FechaRepository fechaRepository) {
        this.fechaRepository = fechaRepository;
    }

    @Override
    public FechaResponseDto crear(FechaRequestDto dto) {

        if (fechaRepository.existsByNombre(dto.getNombre())) {
            throw new FechaDuplicadaException(
                    "Ya existe una fecha con ese nombre"
            );
        }

        Fecha fecha = new Fecha(dto.getNombre());

        Fecha guardada = fechaRepository.save(fecha);

        return mapToResponse(guardada);
    }

    @Override
    public List<FechaResponseDto> listar() {

        return fechaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void actualizarEstadoFecha(
            Fecha fecha,
            List<EstadoPartido> estados
    ) {

        if (estados.isEmpty()) {
            fecha.setEstado(EstadoFecha.PROGRAMADA);
            return;
        }

        boolean algunoEnJuego = estados.stream()
                .anyMatch(e -> e == EstadoPartido.EN_JUEGO);

        if (algunoEnJuego) {
            fecha.setEstado(EstadoFecha.EN_JUEGO);
            return;
        }

        boolean todosFinalizados = estados.stream()
                .allMatch(e -> e == EstadoPartido.FINALIZADO);

        if (todosFinalizados) {
            fecha.setEstado(EstadoFecha.FINALIZADA);
            return;
        }

        fecha.setEstado(EstadoFecha.PROGRAMADA);
    }

    private FechaResponseDto mapToResponse(Fecha fecha) {

        return new FechaResponseDto(
                fecha.getId(),
                fecha.getNombre(),
                fecha.getEstado()
        );
    }
}