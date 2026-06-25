package com.programacion4tpi.prode.feature.pronostico.repository;

import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IPronosticoRepository extends JpaRepository<Pronostico, Long> {
    boolean existsByUsuarioIdAndPartidoId(Long usuarioId, Long partidoId);
    Optional<Pronostico> findByUsuarioIdAndPartidoId(Long usuarioId, Long partidoId);
    List<Pronostico> findByPartidoId(Long partidoId);
    List<Pronostico> findByUsuarioId(Long usuarioId);
}
