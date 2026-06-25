package com.programacion4tpi.prode.feature.pronostico.services.impl;

import com.programacion4tpi.prode.exceptions.global.BadRequestException;
import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.partido.services.domain.ValidatePartido;
import com.programacion4tpi.prode.feature.pronostico.repository.IPronosticoRepository;
import com.programacion4tpi.prode.feature.pronostico.mappers.PronosticoMapper;
import com.programacion4tpi.prode.feature.pronostico.dtos.request.PronosticoCreateRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.response.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import com.programacion4tpi.prode.feature.pronostico.services.impl.interfaces.IPronosticoPostService;
import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import com.programacion4tpi.prode.feature.usuario.services.domain.interfaces.IValidateUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
@AllArgsConstructor
public class PronosticoPostService implements IPronosticoPostService {

    private final IPronosticoRepository repository;
    private final IValidateUser validateUser;
    private final ValidatePartido validatePartido;
    private final PronosticoMapper mapper;

    @Override
    @Transactional
    public PronosticoResponseDto create(PronosticoCreateRequestDto dto) {

        Usuario usuario = validateUser.getAuthenticatedUserSession();

        if (repository.existsByUsuarioIdAndPartidoId(usuario.getId(), dto.partidoId())) {
            throw new BadRequestException("Ya existe un pronóstico para este partido");
        }

        Partido partido = validatePartido.validatePartidoById(dto.partidoId());

        Instant ahora = Instant.now();
        if (!ahora.isBefore(partido.getFechaHoraInicio().minus(30, ChronoUnit.MINUTES))) {
            throw new BadRequestException(
                    "No se pueden realizar pronósticos cuando faltan menos de 30 minutos para el partido"
            );
        }

        Pronostico pronostico = mapper.toEntity(dto);
        pronostico.setUsuario(usuario);
        pronostico.setPartido(partido);

        Pronostico saved = repository.save(pronostico);
        return mapper.toDto(saved);
    }
}