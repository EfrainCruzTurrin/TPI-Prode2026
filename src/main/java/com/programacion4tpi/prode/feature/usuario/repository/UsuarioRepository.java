package com.programacion4tpi.prode.feature.usuario.repository;

import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Usuario> findByUsername(String username);
}