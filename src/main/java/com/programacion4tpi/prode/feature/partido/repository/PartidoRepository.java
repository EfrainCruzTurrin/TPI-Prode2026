package com.programacion4tpi.prode.feature.partido.repository;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    // Verifica si ya existe un partido en la misma fecha con los mismos equipos
    // (en cualquier combinación: local/visitante o visitante/local)
    @Query("""
        SELECT COUNT(p) > 0 FROM Partido p
        WHERE p.fecha.id = :fechaId
        AND (
            (p.equipoLocal.id = :equipoLocalId AND p.equipoVisitante.id = :equipoVisitanteId)
            OR
            (p.equipoLocal.id = :equipoVisitanteId AND p.equipoVisitante.id = :equipoLocalId)
        )
    """)
    boolean existePartidoEnFechaEntreEquipos(
            @Param("fechaId") Long fechaId,
            @Param("equipoLocalId") Long equipoLocalId,
            @Param("equipoVisitanteId") Long equipoVisitanteId
    );
}