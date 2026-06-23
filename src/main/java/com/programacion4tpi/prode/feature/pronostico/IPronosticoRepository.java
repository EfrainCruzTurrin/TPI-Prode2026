package com.programacion4tpi.prode.feature.pronostico;

import com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPronosticoRepository extends JpaRepository<Pronostico, Long> {

    boolean existsByUsuarioIdAndPartidoId(Long usuarioId, Long partidoId);

    Optional<Pronostico> findByUsuarioIdAndPartidoId(Long usuarioId, Long partidoId);

    List<Pronostico> findByPartidoIdAndUsuarioIdNot(Long partidoId, Long usuarioId);

    @Query("""
        SELECT new com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto(
            p.usuario.id,
            p.usuario.username,
            CAST(SUM(p.puntosOtorgados) AS integer),
            SUM(CASE WHEN p.puntosOtorgados = 3 THEN 1L ELSE 0L END)
        )
        FROM Pronostico p
        WHERE p.puntosOtorgados IS NOT NULL
        GROUP BY p.usuario.id, p.usuario.username
        ORDER BY SUM(p.puntosOtorgados) DESC,
                 SUM(CASE WHEN p.puntosOtorgados = 3 THEN 1L ELSE 0L END) DESC,
                 MIN(p.fechaCreacion) ASC
    """)
    List<RankingResponseDto> findRanking();
}