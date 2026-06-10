package com.programacion4tpi.prode.feature.fecha.models;

import jakarta.persistence.*;

@Entity
@Table(name = "fecha_jornada")
public class Fecha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoFecha estado;

    public Fecha() {
    }

    public Fecha(String nombre) {
        this.nombre = nombre;
        this.estado = EstadoFecha.PROGRAMADA;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoFecha getEstado() {
        return estado;
    }

    public void setEstado(EstadoFecha estado) {
        this.estado = estado;
    }
}