package com.programacion4tpi.prode.feature.pronostico.services;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.partido.repository.PartidoRepository;
import com.programacion4tpi.prode.feature.pronostico.IPronosticoRepository;
import com.programacion4tpi.prode.feature.pronostico.PronosticoMapper;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoGetResponseDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.interfaces.IPronosticoGetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PronosticoGetService implements IPronosticoGetService {

    private final IPronosticoRepository pronosticoRepository;
    private final PartidoRepository partidoRepository;  // <-- nombre correcto
    private final PronosticoMapper pronosticoMapper;

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
    public List<RankingResponseDto> getRanking() {
        return pronosticoRepository.findRanking();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PronosticoGetResponseDto> getPronosticosDeOtroUsuario(Long partidoId, Long usuarioSolicitanteId) {
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Partido no encontrado"));

        if (partido.getEstado() != Partido.EstadoPartido.FINALIZADO) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "No podés ver pronósticos de otros usuarios mientras el partido no haya finalizado");
        }

        return pronosticoRepository
                .findByPartidoIdAndUsuarioIdNot(partidoId, usuarioSolicitanteId)
                .stream()
                .map(pronosticoMapper::toGetResponseDto)
                .toList();
    }
}