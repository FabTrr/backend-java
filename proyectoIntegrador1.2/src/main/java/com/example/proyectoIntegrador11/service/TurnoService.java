package com.example.proyectoIntegrador11.service;

import com.example.proyectoIntegrador11.entity.Odontologo;
import com.example.proyectoIntegrador11.entity.Paciente;
import com.example.proyectoIntegrador11.entity.Turno;
import com.example.proyectoIntegrador11.entity.TurnoDTO;
import com.example.proyectoIntegrador11.exception.BadRequestException;
import com.example.proyectoIntegrador11.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

import jakarta.persistence.EntityNotFoundException;

import java.util.*;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private ObjectMapper mapper;

    public List<TurnoDTO> buscarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDTO> turnoDTOS = new ArrayList<>();
        for (Turno turno : turnos) {
            turnoDTOS.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        return turnoDTOS;
    }

    public Turno guardarTurno(TurnoDTO turnoDTO) throws EntityNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(turnoDTO.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(turnoDTO.getOdontologo().getId());

        if (!pacienteBuscado.isPresent()) {
            throw new EntityNotFoundException("El paciente con ID " + turnoDTO.getPaciente().getId() + " no existe");
        }

        if (!odontologoBuscado.isPresent()) {
            throw new EntityNotFoundException("El odontologo con ID " + turnoDTO.getOdontologo().getId() + " no existe");
        }

        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        return turnoRepository.save(turno);
    }

    public Optional<TurnoDTO> buscarTurnoPorId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()) {
            TurnoDTO turnoDTO = mapper.convertValue(turno.get(), TurnoDTO.class);
            return Optional.of(turnoDTO);
        }
        return Optional.empty();
    }

    public void actualizarTurno(TurnoDTO turnoDTO) throws BadRequestException {
        //verifica que el turno existe antes de actualizarlo
        Optional<Turno> turnoExistente = turnoRepository.findById(turnoDTO.getId());
        if (turnoExistente.isPresent()) {
            //verifica si el paciente existe
            if (!pacienteService.buscarPacientePorId(turnoDTO.getPaciente().getId()).isPresent()) {
                throw new BadRequestException("Paciente con ID " + turnoDTO.getPaciente().getId() + " no existe.");
            }
            //verifica si el odontologo existe
            if (!odontologoService.buscarOdontologoPorId(turnoDTO.getOdontologo().getId()).isPresent()) {
                throw new BadRequestException("Odontólogo con ID " + turnoDTO.getOdontologo().getId() + " no existe.");
            }
            Turno turno = mapper.convertValue(turnoDTO, Turno.class);
            turnoRepository.save(turno);
        } else {
            throw new BadRequestException("Turno con ID " + turnoDTO.getId() + " no existe.");
        }
    }

    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }
}
