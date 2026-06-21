// feature/pronostico/repository/PronosticoRepository.java
package com.programacion4tpi.prode.feature.pronostico.repository;

import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PronosticoRepository extends JpaRepository<Pronostico, Long> {
    List<Pronostico> findByPartidoId(Long partidoId);
}