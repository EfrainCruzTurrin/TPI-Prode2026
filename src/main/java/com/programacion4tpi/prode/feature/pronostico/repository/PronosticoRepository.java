// feature/pronostico/repository/PronosticoRepository.java
package com.programacion4tpi.prode.feature.pronostico.repository;

import com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PronosticoRepository extends JpaRepository<Pronostico, Long> {
    List<Pronostico> findByPartidoId(Long partidoId);

    @Query("""
    SELECT new com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto(
        p.usuario.id,
        p.usuario.username,
        SUM(p.puntos),
        SUM(CASE WHEN p.esPleno = true THEN 1 ELSE 0 END)
    )
    FROM Pronostico p
    GROUP BY p.usuario.id, p.usuario.username
    ORDER BY SUM(p.puntos) DESC,
             SUM(CASE WHEN p.esPleno = true THEN 1 ELSE 0 END) DESC,
             MIN(p.createdAt) ASC
    """)
    List<RankingResponseDto> findRanking();
}