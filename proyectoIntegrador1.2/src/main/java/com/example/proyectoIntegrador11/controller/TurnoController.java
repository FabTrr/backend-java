package com.example.proyectoIntegrador11.controller;

import com.example.proyectoIntegrador11.entity.Odontologo;
import com.example.proyectoIntegrador11.entity.Paciente;
import com.example.proyectoIntegrador11.entity.Turno;
import com.example.proyectoIntegrador11.entity.TurnoDTO;
import com.example.proyectoIntegrador11.exception.BadRequestException;
import com.example.proyectoIntegrador11.exception.ResourceNotFoundException;
import com.example.proyectoIntegrador11.service.OdontologoService;
import com.example.proyectoIntegrador11.service.PacienteService;
import com.example.proyectoIntegrador11.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping("/registrar")
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.registrarTurno(turno));
        }
        throw new BadRequestException("El paciente y/o odontologo no está registrado en la base de datos");
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTodosLosTurnos(){
        List<TurnoDTO> turnos = turnoService.listarTodos();
        if (turnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turnos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado");
        }
        throw new ResourceNotFoundException("No existe un turno con id: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable("id") Long id) {
        Optional<TurnoDTO> turno = turnoService.buscarTurnoId(id);
        if (turno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(turno.get());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO) {
        if (turnoDTO.getId() == null) {
            return ResponseEntity.badRequest().body("El ID del turno no puede ser nulo");
        }

        Optional<TurnoDTO> turnoExistente = turnoService.buscarTurnoId(turnoDTO.getId());
        if (turnoExistente.isPresent()) {
            // Cargar el Odontologo desde la base de datos usando el ID
            Optional<Odontologo> odontologo = odontologoService.buscarOdontologoPorId(turnoDTO.getOdontologoId());
            if (odontologo.isPresent()) {
                // Convertir TurnoDTO a Turno
                Turno turnoActualizado = turnoService.turnodtoAturno(turnoDTO);
                turnoActualizado.setOdontologo(odontologo.get());

                // Actualizar el turno en la base de datos
                turnoService.actualizarTurno(turnoDTO);

                return ResponseEntity.ok("Turno actualizado correctamente");
            } else {
                return ResponseEntity.notFound().build(); // Odontologo no encontrado
            }
        } else {
            return ResponseEntity.notFound().build(); // Turno no encontrado
        }
    }
}