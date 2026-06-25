package com.programacion4tpi.prode.feature.pronostico.services.impl;

import com.programacion4tpi.prode.feature.pronostico.repository.IPronosticoRepository;
import com.programacion4tpi.prode.feature.pronostico.mappers.PronosticoMapper;
import com.programacion4tpi.prode.feature.pronostico.dtos.response.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.impl.interfaces.IPronosticoGetService;
import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import com.programacion4tpi.prode.feature.usuario.services.domain.ValidateUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PronosticoGetService implements IPronosticoGetService {

    private final IPronosticoRepository pronosticoRepository;
    private final PronosticoMapper pronosticoMapper;
    private final ValidateUser validateUser;

    @Override
    @Transactional(readOnly = true)
    public List<PronosticoResponseDto> listarPronosticos() {
        return pronosticoRepository.findAll()
                .stream()
                .map(pronosticoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PronosticoResponseDto> listarMisPronosticos() {

        Usuario usuario = validateUser.getAuthenticatedUserSession();

        return pronosticoRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(pronosticoMapper::toDto)
                .toList();
    }
}
