package com.programacion4tpi.prode.feature.equipo.controllers.post;

import com.programacion4tpi.prode.config.BaseResponse;
import com.programacion4tpi.prode.feature.equipo.dtos.request.EquipoCreateRequestDto;
import com.programacion4tpi.prode.feature.equipo.dtos.response.EquipoResponseDto;
import com.programacion4tpi.prode.feature.equipo.services.interfaces.EquipoService;
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
    public ResponseEntity<BaseResponse<EquipoResponseDto>> crearEquipo(
            @Valid @RequestBody EquipoCreateRequestDto requestDto) {

        EquipoResponseDto response = equipoService.crearEquipo(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.ok(response, "Equipo creado correctamente"));
    }
}
