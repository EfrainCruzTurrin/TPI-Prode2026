package com.programacion4tpi.prode.feature.partido.controllers.get;

import com.programacion4tpi.prode.feature.partido.dtos.response.HistorialPartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.service.impl.PartidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
public class HistorialGetController {

    private final PartidoService partidoService;

    @GetMapping("/historial/{equipoId}")
    public ResponseEntity<List<HistorialPartidoResponseDto>> getHistorialByEquipo(@PathVariable Long equipoId) {
        List<HistorialPartidoResponseDto> historial = partidoService.obtenerHistorialPorEquipo(equipoId);
        return ResponseEntity.ok(historial);
    }
}