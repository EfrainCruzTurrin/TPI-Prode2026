package com.programacion4tpi.prode.feature.usuario.dtos.request;

public record RefreshRequestDto (
        String accessToken,
        String tokenType,
        long expiresIn
) {}
