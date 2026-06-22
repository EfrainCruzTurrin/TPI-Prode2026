package com.programacion4tpi.prode.feature.pronostico.services.interfaces;

import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoGetResponseDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto;

import java.util.List;

public interface IPronosticoGetService {
    List<PronosticoResponseDto> listarPronosticos();
    List<RankingResponseDto> getRanking();
    List<PronosticoGetResponseDto> getPronosticosDeOtroUsuario(Long partidoId, Long usuarioSolicitanteId);
}