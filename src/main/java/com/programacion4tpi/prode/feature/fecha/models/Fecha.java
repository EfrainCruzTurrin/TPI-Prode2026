package com.programacion4tpi.prode.feature.fecha.models;

import com.programacion4tpi.prode.feature.fecha.models.enums.EstadoFecha;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "fecha")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Fecha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoFecha estado;

}