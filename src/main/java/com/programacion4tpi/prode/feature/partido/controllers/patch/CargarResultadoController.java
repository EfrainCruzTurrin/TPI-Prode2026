package com.programacion4tpi.prode.feature.partido.controllers.patch;

import com.programacion4tpi.prode.config.BaseResponse;
import com.programacion4tpi.prode.feature.partido.dtos.request.CargarResultadoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.services.impl.intefaces.PartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CargarResultadoController {

    private final PartidoService partidoService;

    @PatchMapping("/{id}/resultado")
    public ResponseEntity<BaseResponse<PartidoResponseDto>> cargarResultado(
            @PathVariable Long id,
            @Valid @RequestBody CargarResultadoRequestDto dto) {

        PartidoResponseDto response = partidoService.cargarResultado(id, dto);

        return ResponseEntity.ok(BaseResponse.ok(
                response, "Resultados cargados correctamente"
        ));
    }
}