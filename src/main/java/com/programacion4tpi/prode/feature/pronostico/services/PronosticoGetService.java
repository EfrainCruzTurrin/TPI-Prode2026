package com.programacion4tpi.prode.feature.pronostico.services;

import com.programacion4tpi.prode.feature.pronostico.IPronosticoRepository;
import com.programacion4tpi.prode.feature.pronostico.PronosticoMapper;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.interfaces.IPronosticoGetService;
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

    @Override
    @Transactional(readOnly = true)
    public List<PronosticoResponseDto> listarPronosticos() {
        return pronosticoRepository.findAll()
                .stream()
                .map(pronosticoMapper::toDto)
                .collect(Collectors.toList());
    }
}
