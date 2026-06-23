package com.programacion4tpi.prode.feature.equipo.controllers.delete;

import com.programacion4tpi.prode.feature.equipo.service.domain.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class EquipoDeleteController {

    private final EquipoService equipoService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}