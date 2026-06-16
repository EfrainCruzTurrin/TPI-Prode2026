package com.programacion4tpi.prode.feature.fecha.repository;

import com.programacion4tpi.prode.feature.fecha.models.Fecha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FechaRepository extends JpaRepository<Fecha, Long> {

    boolean existsByNombre(String nombre);
}