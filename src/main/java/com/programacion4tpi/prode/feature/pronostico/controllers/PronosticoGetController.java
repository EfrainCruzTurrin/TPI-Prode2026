package com.programacion4tpi.prode.feature.pronostico.controllers;

import com.programacion4tpi.prode.feature.pronostico.dtos.response.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.impl.interfaces.IPronosticoGetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/pronosticos")
@AllArgsConstructor
public class PronosticoGetController {

    private final IPronosticoGetService pronosticoGetService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PronosticoResponseDto>> listarPronosticos() {
        return ResponseEntity.ok(pronosticoGetService.listarPronosticos());
    }

    // listar pronósticos del usuario
}
