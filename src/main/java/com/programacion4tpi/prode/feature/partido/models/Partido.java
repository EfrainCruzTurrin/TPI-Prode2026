package com.programacion4tpi.prode.feature.partido.models;

import com.programacion4tpi.prode.feature.equipo.models.Equipo;
import com.programacion4tpi.prode.feature.fecha.models.Fecha;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "partido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fecha_id", nullable = false)
    private Fecha fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local_id", nullable = false)
    private Equipo equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visitante_id", nullable = false)
    private Equipo equipoVisitante;

    // Utilizamos Instant para forzar y garantizar la persistencia en formato UTC
    @Column(name = "fecha_hora_inicio", nullable = false)
    private Instant fechaHoraInicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EstadoPartido estado = EstadoPartido.POR_JUGARSE;

    @Column(name = "goles_local")
    private Integer golesLocal;

    @Column(name = "goles_visitante")
    private Integer golesVisitante;

    @Enumerated(EnumType.STRING)
    private ResultadoPartido resultado;

    public enum EstadoPartido {
        POR_JUGARSE, EN_JUEGO, FINALIZADO
    }

    public enum ResultadoPartido {
        LOCAL, EMPATE, VISITANTE
    }
}