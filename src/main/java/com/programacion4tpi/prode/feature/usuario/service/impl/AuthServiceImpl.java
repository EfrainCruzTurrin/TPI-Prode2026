package com.programacion4tpi.prode.feature.usuario.service.impl;

import com.programacion4tpi.prode.config.JwtService;
import com.programacion4tpi.prode.feature.usuario.dtos.request.*;
import com.programacion4tpi.prode.feature.usuario.dtos.response.AuthResponseDto;
import com.programacion4tpi.prode.feature.usuario.models.*;
import com.programacion4tpi.prode.feature.usuario.repository.UsuarioRepository;
import com.programacion4tpi.prode.feature.usuario.service.domain.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.JUGADOR)
                .puntosTotales(0)
                .resultadosExactos(0)
                .build();

        usuarioRepository.save(usuario);
        String token = jwtService.generateToken(usuario);
        return buildResponse(usuario, token);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String token = jwtService.generateToken(usuario);
        return buildResponse(usuario, token);
    }

    private AuthResponseDto buildResponse(Usuario usuario, String token) {
        return AuthResponseDto.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .rol(usuario.getRol().name())
                .token(token)
                .build();
    }
}