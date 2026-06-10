package com.programacion4tpi.prode.feature.usuario.dtos.request;


public record RegisterRequestDto (
      String username,
      String email,
      String password
) {
}