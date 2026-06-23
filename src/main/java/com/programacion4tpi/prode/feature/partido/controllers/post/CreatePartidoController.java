package com.programacion4tpi.prode.feature.partido.controllers.post;

import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.service.impl.PartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CreatePartidoController {

    private final PartidoService partidoService;

    @PostMapping
    public ResponseEntity<PartidoResponseDto> create(@Valid @RequestBody PartidoRequestDto dto) {
        PartidoResponseDto response = partidoService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}