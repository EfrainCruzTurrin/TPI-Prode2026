package com.programacion4tpi.prode.feature.usuario.services.impl.interfaces;

import com.programacion4tpi.prode.feature.usuario.dtos.request.LoginRequestDto;
import com.programacion4tpi.prode.feature.usuario.dtos.request.RefreshRequestDto;
import com.programacion4tpi.prode.feature.usuario.dtos.request.RegisterRequestDto;
import com.programacion4tpi.prode.feature.usuario.dtos.response.AuthResponseDto;
import com.programacion4tpi.prode.feature.usuario.dtos.response.RefreshResponseDto;

public interface IAuthService {
    void register(RegisterRequestDto request);
    AuthResponseDto login(LoginRequestDto request);
    RefreshResponseDto refresh(RefreshRequestDto request);
}
