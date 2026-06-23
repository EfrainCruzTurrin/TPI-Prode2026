package com.programacion4tpi.prode.feature.partido.controllers.get;

import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.service.impl.PartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class GetPartidosController {

    private final PartidoService partidoService;

    @GetMapping
    public ResponseEntity<List<PartidoResponseDto>> listar() {
        return ResponseEntity.ok(partidoService.listar());
    }
}
