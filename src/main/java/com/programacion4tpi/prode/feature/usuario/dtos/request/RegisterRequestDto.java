package com.programacion4tpi.prode.feature.usuario.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record RegisterRequestDto (
        @NotBlank(message = "El usuario es obligatorio")
        @Size(min = 3, max = 64, message = "El usuario debe tener entre 3 y 64 caracteres")
        @Pattern(
                regexp = "^[a-zA-Z0-9_.]+$",
                message = "El usuario solo puede contener letras, números, guion bajo y punto"
        )
        String username,

        @NotBlank(message = "El email es obligatorio")
        @Size(max = 35)
        @Email
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
        String password
) {
}