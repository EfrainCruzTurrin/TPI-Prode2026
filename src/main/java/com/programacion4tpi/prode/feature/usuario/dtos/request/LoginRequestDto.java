package com.programacion4tpi.prode.feature.usuario.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record LoginRequestDto(
        @NotBlank(message = "El email es obligatorio")
        @Size(max = 35)
        @Email
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(max = 20)
        String password
) {
}