package com.programacion4tpi.prode.feature.usuario.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record LoginRequestDto(
        @NotBlank(message = "El usuario es obligatorio")
        @Size(max = 64)
        String username,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(max = 128)
        String password
) {
}