package com.programacion4tpi.prode.feature.pronostico.controllers;

import com.programacion4tpi.prode.config.BaseResponse;
import com.programacion4tpi.prode.feature.pronostico.dtos.req.PronosticoModifyRequestDto;
import com.programacion4tpi.prode.feature.pronostico.dtos.resp.PronosticoResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.interfaces.IPronosticoModifyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pronostico")
@AllArgsConstructor
public class PronosticoPatchController {

    private final IPronosticoModifyService pronosticoModifyService;

    @PatchMapping
    public ResponseEntity<BaseResponse<PronosticoResponseDto>> actualizarPronostico(
            @Valid @RequestBody PronosticoModifyRequestDto dto
    ) {
        PronosticoResponseDto pronostico = pronosticoModifyService.actualizarPronostico(dto);

        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.ok(pronostico, "Pronóstico actualizado correctamente")
        );
    }
}
