package com.programacion4tpi.prode.feature.usuario.services.impl;

import com.programacion4tpi.prode.config.jwt.JwtProperties;
import com.programacion4tpi.prode.config.jwt.JwtService;
import com.programacion4tpi.prode.exceptions.jwt.InvalidCredentialsException;
import com.programacion4tpi.prode.exceptions.jwt.UserAlreadyExistsException;
import com.programacion4tpi.prode.feature.usuario.dtos.request.*;
import com.programacion4tpi.prode.feature.usuario.dtos.response.AuthResponseDto;
import com.programacion4tpi.prode.feature.usuario.dtos.response.RefreshResponseDto;
import com.programacion4tpi.prode.feature.usuario.models.*;
import com.programacion4tpi.prode.feature.usuario.models.enums.Rol;
import com.programacion4tpi.prode.feature.usuario.repository.UsuarioRepository;
import com.programacion4tpi.prode.feature.usuario.services.domain.interfaces.IAuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private static final String TOKEN_TYPE_BEARER = "Bearer";
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;


    @Transactional
    @Override
    public void register(RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("El usuario existe");
        }

        Usuario user = Usuario.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .rol(Rol.JUGADOR)
                .build();
        userRepository.save(user);
    }


    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        try {
            // Encapsular la autenticación en un try-catch para manejar el caso de credenciales inválidas
            // Si las credenciales son inválidas, se lanza una excepción de tipo InvalidCredentialsException
            Authentication authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(request.username(), request.password())
            );

            // Obtener el usuario autenticado
            UserDetails principal = (UserDetails) authentication.getPrincipal();

            // Obtener los roles del usuario
            assert principal != null;
            var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

            // Generar el token JWT
            String accessToken = jwtService.generateToken(principal.getUsername(), roles);
            String refreshToken = jwtService.generateRefreshToken(principal.getUsername());

            // Devolver el token JWT
            return new AuthResponseDto(accessToken, refreshToken, TOKEN_TYPE_BEARER, jwtProperties.expirationMs());

            // Si las credenciales son inválidas, se lanza una excepción de tipo InvalidCredentialsException
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Credenciales invalidas");
        }
    }

    public RefreshResponseDto refresh(RefreshRequestDto request) {
        Claims claims = jwtService.parseValidClaims(request.refreshToken())	// obtiene token del cliente y lo parsea para ver validez
                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));	// void en caso de error (401)

        String username = claims.getSubject();

        Usuario user = userRepository.findByUsername(username)	// validación contra DB (usuario eliminado)
                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));

        var roles = List.of(user.getRol().name());	// rol

        String newAccessToken = jwtService.generateToken(username, roles);	// nuevo token

        return new RefreshResponseDto(
                newAccessToken,
                "Bearer",
                jwtProperties.expirationMs()
        );
    }
}