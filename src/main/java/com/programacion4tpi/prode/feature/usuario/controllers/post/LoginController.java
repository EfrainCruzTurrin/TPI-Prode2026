package com.programacion4tpi.prode.feature.usuario.controllers.post;

import com.programacion4tpi.prode.feature.usuario.dtos.request.LoginRequestDto;
import com.programacion4tpi.prode.feature.usuario.dtos.response.AuthResponseDto;
import com.programacion4tpi.prode.feature.usuario.service.domain.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}