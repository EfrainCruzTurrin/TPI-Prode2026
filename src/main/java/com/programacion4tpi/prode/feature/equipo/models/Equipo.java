package com.programacion4tpi.prode.feature.equipo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "escudo_url", length = 150)
    private String escudoUrl;
}