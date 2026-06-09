package com.programacion4tpi.prode.feature.usuario.dtos.response;

import lombok.*;

@Getter @Setter @Builder
public class AuthResponseDto {
    private Long id;
    private String username;
    private String email;
    private String rol;
    private String token;
}