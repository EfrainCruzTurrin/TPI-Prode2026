package com.programacion4tpi.prode.feature.partido.repository;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
    // Busca partidos finalizados donde el equipo sea local O visitante
    @Query("SELECT p FROM Partido p WHERE (p.equipoLocal.id = :equipoId OR p.equipoVisitante.id = :equipoId) " +
            "AND p.estadoPartido = :estado ORDER BY p.fecha.id DESC")
    List<Partido> findHistorialByEquipo(@Param("equipoId") Long equipoId, @Param("estado") Partido.EstadoPartido estado);
}
