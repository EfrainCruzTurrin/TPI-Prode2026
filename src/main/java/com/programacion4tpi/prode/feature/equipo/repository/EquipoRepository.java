package com.programacion4tpi.prode.feature.equipo.repository;

import com.programacion4tpi.prode.feature.equipo.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    Optional<Equipo> findByNombreIgnoreCase(String nombre);
}