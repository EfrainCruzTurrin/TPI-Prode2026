package com.programacion4tpi.prode.feature.pronostico.service.domain;

import com.programacion4tpi.prode.feature.partido.models.Partido;

public interface PuntuacionService {
    void calcularYAsignarPuntos(Partido partido);
}