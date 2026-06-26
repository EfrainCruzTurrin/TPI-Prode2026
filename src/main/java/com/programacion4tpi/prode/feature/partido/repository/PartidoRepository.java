package com.programacion4tpi.prode.feature.partido.repository;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    List<Partido> findByFechaId(Long fechaId);

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