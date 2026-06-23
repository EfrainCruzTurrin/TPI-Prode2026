package com.programacion4tpi.prode.feature.pronostico.controllers;

import com.programacion4tpi.prode.feature.pronostico.dtos.resp.RankingResponseDto;
import com.programacion4tpi.prode.feature.pronostico.services.interfaces.IPronosticoGetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingGetController {

    private final IPronosticoGetService pronosticoGetService;

    public RankingGetController(IPronosticoGetService pronosticoGetService) {
        this.pronosticoGetService = pronosticoGetService;
    }

    @GetMapping
    public ResponseEntity<List<RankingResponseDto>> getRanking() {
        List<RankingResponseDto> ranking = pronosticoGetService.getRanking();
        return ResponseEntity.ok(ranking);
    }
}