package com.programacion4tpi.prode.feature.pronostico.controllers;

import com.programacion4tpi.prode.config.BaseResponse;
import com.programacion4tpi.prode.feature.pronostico.dtos.req.PronosticoCreateRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.interfaces.IPronosticoPostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pronosticos")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PronosticoPostController {

    private final IPronosticoPostService pronosticoPostService;

    @PostMapping
    public ResponseEntity<BaseResponse<PronosticoResponseDto>> crearPronostico(
            @Valid @RequestBody PronosticoCreateRequestDto dto
    ) {

        PronosticoResponseDto pronostico = pronosticoPostService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.ok(pronostico, "Pronóstico creado correctamente"));
    }
}
