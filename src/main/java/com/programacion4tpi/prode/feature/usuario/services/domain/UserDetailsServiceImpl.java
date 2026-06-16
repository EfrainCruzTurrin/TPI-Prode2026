package com.programacion4tpi.prode.feature.usuario.services.domain;

import com.programacion4tpi.prode.feature.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository userRepository;

    /*
        para simplificar: se modifica el método loadUserByUsername para que reciba un mail
    */

    @Override
    public UserDetails loadUserByUsername(@NonNull String mail)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));
    }
}