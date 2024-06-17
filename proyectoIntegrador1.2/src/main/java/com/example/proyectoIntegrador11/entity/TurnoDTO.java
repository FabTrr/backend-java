package com.example.proyectoIntegrador11.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TurnoDTO {

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Paciente paciente;
    private Odontologo odontologo;


    public Integer getId() {
        return id;
    }

}
