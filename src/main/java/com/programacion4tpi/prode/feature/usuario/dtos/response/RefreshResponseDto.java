package com.programacion4tpi.prode.feature.usuario.dtos.response;

public record RefreshResponseDto (
        String accessToken,
        String tokenType,
        long expiresIn
) {}