package com.programacion4tpi.prode.feature.pronostico.services.impl;

import com.programacion4tpi.prode.exceptions.global.BadRequestException;
import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.partido.services.domain.ValidatePartido;
import com.programacion4tpi.prode.feature.pronostico.repository.IPronosticoRepository;
import com.programacion4tpi.prode.feature.pronostico.mappers.PronosticoMapper;
import com.programacion4tpi.prode.feature.pronostico.dtos.request.PronosticoModifyRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.response.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import com.programacion4tpi.prode.feature.pronostico.services.impl.interfaces.IPronosticoModifyService;
import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import com.programacion4tpi.prode.feature.usuario.services.domain.interfaces.IValidateUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class PronosticoModifyService implements IPronosticoModifyService {

    private final IPronosticoRepository repository;
    private final ValidatePartido validatePartido;
    private final PronosticoMapper mapper;
    private final IValidateUser validateUser;


    @Override
    @Transactional
    public PronosticoResponseDto actualizarPronostico(PronosticoModifyRequestDto dto) {

        Usuario usuario = validateUser.getAuthenticatedUserSession();
        Partido partido = validatePartido.validatePartidoById(dto.partidoId());

        Instant ahora = Instant.now();
        if (!ahora.isBefore(partido.getFechaHoraInicio().minus(30, ChronoUnit.MINUTES))) {
            throw new BadRequestException(
                    "No se pueden realizar pronósticos cuando faltan menos de 30 minutos para el partido"
            );
        }

        Pronostico pronostico = repository
                .findByUsuarioIdAndPartidoId(usuario.getId(), partido.getId())
                .orElseThrow(() ->
                        new BadRequestException("No existe un pronóstico para este partido")
                );

        if (dto.golesLocalPredicho() != null) {
            pronostico.setGolesLocalPredicho(dto.golesLocalPredicho());
        }

        if (dto.golesVisitantePredicho() != null) {
            pronostico.setGolesVisitantePredicho(dto.golesVisitantePredicho());
        }

        Pronostico actualizado = repository.save(pronostico);

        return mapper.toDto(actualizado);
    }
}
