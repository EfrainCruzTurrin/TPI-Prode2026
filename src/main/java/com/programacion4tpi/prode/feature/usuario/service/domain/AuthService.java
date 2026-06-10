package com.programacion4tpi.prode.feature.usuario.service.domain;

import com.programacion4tpi.prode.feature.usuario.dtos.request.*;
import com.programacion4tpi.prode.feature.usuario.dtos.response.AuthResponseDto;

public interface AuthService {
    AuthResponseDto register(RegisterRequestDto request);
    AuthResponseDto login(LoginRequestDto request);
}