package com.programacion4tpi.prode.feature.fecha.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FechaRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre de la fecha no puede tener más de 30 caracteres")
    private String nombre;

}