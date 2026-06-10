package com.programacion4tpi.prode.feature.fecha.controller;

import com.programacion4tpi.prode.feature.fecha.dtos.request.FechaRequestDto;
import com.programacion4tpi.prode.feature.fecha.dtos.response.FechaResponseDto;
import com.programacion4tpi.prode.feature.fecha.service.domain.FechaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fechas")
public class FechaController {

    private final FechaService fechaService;

    public FechaController(FechaService fechaService) {
        this.fechaService = fechaService;
    }

    @PostMapping
    public ResponseEntity<FechaResponseDto> crear(
            @RequestBody @Valid FechaRequestDto dto
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fechaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<FechaResponseDto>> listar() {

        return ResponseEntity.ok(
                fechaService.listar()
        );
    }
}