package com.programacion4tpi.prode.feature.partido.repository;

import com.programacion4tpi.prode.exceptions.global.PartidoNotFoundException;
import com.programacion4tpi.prode.feature.partido.models.Partido;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidatePartido {
    private final PartidoRepository repository;

    public Partido validatePartidoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PartidoNotFoundException("Partido no encontrado"));
    }

}
