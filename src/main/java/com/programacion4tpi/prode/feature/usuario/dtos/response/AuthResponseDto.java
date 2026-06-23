package com.programacion4tpi.prode.feature.usuario.dtos.response;


public record AuthResponseDto (
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresInMs,
        String username,
        String rol
) {
}