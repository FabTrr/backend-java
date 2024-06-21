package com.example.proyectoIntegrador11.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TurnoDTO {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Long pacienteId;
    private Long odontologoId;
}