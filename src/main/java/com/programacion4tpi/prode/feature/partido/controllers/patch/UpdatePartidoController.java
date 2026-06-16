package com.programacion4tpi.prode.feature.partido.controllers.patch;

import com.programacion4tpi.prode.feature.partido.dtos.request.PartidoUpdateRequestDto;
import com.programacion4tpi.prode.feature.partido.dtos.response.PartidoResponseDto;
import com.programacion4tpi.prode.feature.partido.service.impl.PartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
public class UpdatePartidoController {

    private final PartidoService partidoService;

    @PatchMapping("/{id}")
    public ResponseEntity<PartidoResponseDto> update(@PathVariable Long id, @RequestBody PartidoUpdateRequestDto dto) {
        PartidoResponseDto response = partidoService.update(id, dto);
        return ResponseEntity.ok(response);
    }
}
