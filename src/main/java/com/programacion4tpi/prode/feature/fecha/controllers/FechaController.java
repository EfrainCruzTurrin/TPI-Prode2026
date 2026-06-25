package com.programacion4tpi.prode.feature.fecha.controllers;

import com.programacion4tpi.prode.config.BaseResponse;
import com.programacion4tpi.prode.feature.fecha.dtos.request.FechaRequestDto;
import com.programacion4tpi.prode.feature.fecha.dtos.response.FechaResponseDto;
import com.programacion4tpi.prode.feature.fecha.services.interfaces.IFechaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fechas")
@AllArgsConstructor
public class FechaController {

    private final IFechaService fechaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<FechaResponseDto>> crear(
            @RequestBody @Valid FechaRequestDto dto
    ) {

        FechaResponseDto fechaResponseDto = fechaService.crear(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.ok(fechaResponseDto, "Fecha creada correctamente")
        );
    }

    @GetMapping
    public ResponseEntity<List<FechaResponseDto>> listar() {

        return ResponseEntity.ok(
                fechaService.listar()
        );
    }

    // añadir delete para el admin
}