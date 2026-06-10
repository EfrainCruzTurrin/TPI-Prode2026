package com.programacion4tpi.prode.feature.equipo.dtos.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoResponseDto {

    private Long id;
    private String nombre;
    private String pais;  // campo nuevo
    private String escudoUrl;
}