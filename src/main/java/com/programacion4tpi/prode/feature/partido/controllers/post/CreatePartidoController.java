package com.programacion4tpi.prode.feature.partido.controllers.post;

import com.programacion4tpi.prode.config.BaseResponse;
import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoCreateRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.services.impl.intefaces.PartidoService;
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
    public ResponseEntity<BaseResponse<PartidoResponseDto>> create(
            @Valid @RequestBody PartidoCreateRequestDto dto
    ) {
        PartidoResponseDto response = partidoService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.ok(response, "Partido creado correctamente")
        );
    }
}