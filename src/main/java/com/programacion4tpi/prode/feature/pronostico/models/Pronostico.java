package com.programacion4tpi.prode.feature.pronostico.models;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "pronostico", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "partido_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pronostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;

    @Column(name = "goles_local_predicho", nullable = false)
    private Integer golesLocalPredicho;

    @Column(name = "goles_visitante_predicho", nullable = false)
    private Integer golesVisitantePredicho;

    @Column(name = "puntos_otorgados")
    private Integer puntosOtorgados;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.puntosOtorgados = 0;
        this.fechaCreacion = LocalDateTime.now();
    }
}
