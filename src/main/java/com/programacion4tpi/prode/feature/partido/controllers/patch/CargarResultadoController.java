// feature/partido/controllers/patch/CargarResultadoController.java
package com.programacion4tpi.prode.feature.partido.controllers.patch;

import com.programacion4tpi.prode.config.BaseResponse;
import com.programacion4tpi.prode.feature.partido.dtos.request.CargarResultadoRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.service.impl.PartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
public class CargarResultadoController {

    private final PartidoService partidoService;

    @PatchMapping("/{id}/resultado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<PartidoResponseDto>> cargarResultado(
            @PathVariable Long id,
            @Valid @RequestBody CargarResultadoRequestDto dto) {

        PartidoResponseDto response = partidoService.cargarResultado(id, dto);
        return ResponseEntity.ok(BaseResponse.<PartidoResponseDto>builder()
                .data(response)
                .message("Resultado cargado y puntos asignados correctamente.")
                .build());
    }
}