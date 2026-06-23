package com.programacion4tpi.prode.feature.equipo.controllers.post;

import com.programacion4tpi.prode.feature.equipo.dtos.request.EquipoRequestDto;
import com.programacion4tpi.prode.feature.equipo.dtos.response.EquipoResponseDto;
import com.programacion4tpi.prode.feature.equipo.service.domain.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class EquipoPostController {

    private final EquipoService equipoService;

    @PostMapping
    public ResponseEntity<EquipoResponseDto> crearEquipo(
            @Valid @RequestBody EquipoRequestDto requestDto) {
        EquipoResponseDto response = equipoService.crearEquipo(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
