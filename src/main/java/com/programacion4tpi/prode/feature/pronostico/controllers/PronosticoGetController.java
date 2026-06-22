package com.programacion4tpi.prode.feature.pronostico.controllers;

import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoGetResponseDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.interfaces.IPronosticoGetService;
import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pronostico")
@AllArgsConstructor
public class PronosticoGetController {

    private final IPronosticoGetService pronosticoGetService;

    @GetMapping
    public ResponseEntity<List<PronosticoResponseDto>> listarPronosticos() {
        return ResponseEntity.ok(pronosticoGetService.listarPronosticos());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<RankingResponseDto>> getRanking() {
        return ResponseEntity.ok(pronosticoGetService.getRanking());
    }

    @GetMapping("/partido/{partidoId}/terceros")
    public ResponseEntity<List<PronosticoGetResponseDto>> getPronosticosDeOtros(
            @PathVariable Long partidoId,
            Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();
        List<PronosticoGetResponseDto> resultado =
                pronosticoGetService.getPronosticosDeOtroUsuario(partidoId, usuario.getId());

        return ResponseEntity.ok(resultado);
    }
}