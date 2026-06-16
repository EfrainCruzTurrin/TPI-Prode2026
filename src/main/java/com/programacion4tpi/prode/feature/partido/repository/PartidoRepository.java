package com.programacion4tpi.prode.feature.partido.repository;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
    // Hereda todos los métodos CRUD básicos necesarios para la US
}
