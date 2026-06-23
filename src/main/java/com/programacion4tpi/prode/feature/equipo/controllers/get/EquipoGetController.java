package com.programacion4tpi.prode.feature.equipo.controllers.get;

import com.programacion4tpi.prode.feature.equipo.dtos.response.EquipoResponseDto;
import com.programacion4tpi.prode.feature.equipo.service.domain.EquipoService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoGetController {

    private final EquipoService equipoService;

    @GetMapping
    @PermitAll
    public ResponseEntity<List<EquipoResponseDto>> listarEquipos() {
        return ResponseEntity.ok(equipoService.listarEquipos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EquipoResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(equipoService.obtenerEquipoPorId(id));
    }
}