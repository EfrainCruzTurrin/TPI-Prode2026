package com.programacion4tpi.prode.feature.usuario.dtos.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RegisterRequestDto {
    private String username;
    private String email;
    private String password;
}