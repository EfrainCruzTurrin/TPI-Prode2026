package com.programacion4tpi.prode.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
        String secret,  // firma JWT (tokens válidos)
        long expirationMs,  // duración de token
        long refreshExpirationMs, // duración refreshToken
        String algorithm    // algoritmo de firma (generación/verificación de token)
) {
}
