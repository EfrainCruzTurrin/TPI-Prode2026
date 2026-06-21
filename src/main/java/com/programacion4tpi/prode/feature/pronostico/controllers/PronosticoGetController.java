package com.programacion4tpi.prode.feature.pronostico.controllers;

import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.interfaces.IPronosticoGetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
