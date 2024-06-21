package com.example.proyectoIntegrador11;

import com.example.proyectoIntegrador11.entity.*;

import com.example.proyectoIntegrador11.exception.BadRequestException;
import com.example.proyectoIntegrador11.service.OdontologoService;
import com.example.proyectoIntegrador11.service.PacienteService;
import com.example.proyectoIntegrador11.service.TurnoService;
import com.example.proyectoIntegrador11.entity.TurnoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback
class JunitTests {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    private Paciente paciente;
    private Odontologo odontologo;

    @BeforeEach
    void setUp() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle Falsa");
        domicilio.setNumero(123L);
        domicilio.setLocalidad("Springfield");
        domicilio.setProvincia("Provincia Falsa");

        paciente = new Paciente();
        paciente.setNombre("Maria");
        paciente.setApellido("Lopez");
        paciente.setCedula("121212");
        paciente.setFechaIngreso(LocalDate.of(2023, 1, 1));
        paciente.setDomicilio(domicilio);
        paciente.setEmail("hola@maria.com");

        odontologo = new Odontologo();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Gomez");
        odontologo.setNumeroMatricula(87654321L);

        paciente = pacienteService.guardarPaciente(paciente);
        odontologo = odontologoService.guardarOdontologo(odontologo);
    }

    /***** TEST PARA AGREGAR PACIENTE *****/
    @Test
    public void agregarPaciente() {
        // DADO
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Castillo de Bran");
        domicilio.setNumero(24L);
        domicilio.setLocalidad("Transilvania");
        domicilio.setProvincia("Brasov");

        Paciente paciente = new Paciente();
        paciente.setNombre("Conde");
        paciente.setApellido("Dracula");
        paciente.setCedula("111111");
        paciente.setFechaIngreso(LocalDate.of(1885, 1, 1));
        paciente.setDomicilio(domicilio);
        paciente.setEmail("hola@soydracula.com");

        // CUANDO
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);

        // ENTONCES
        Assertions.assertNotNull(pacienteGuardado);
        Assertions.assertNotNull(pacienteGuardado.getId());
        Assertions.assertEquals("Conde", pacienteGuardado.getNombre());
        Assertions.assertEquals("Dracula", pacienteGuardado.getApellido());
        Assertions.assertEquals("111111", pacienteGuardado.getCedula());
        Assertions.assertEquals(LocalDate.of(1885, 1, 1), pacienteGuardado.getFechaIngreso());
        Assertions.assertNotNull(pacienteGuardado.getDomicilio());
        Assertions.assertEquals("Castillo de Bran", pacienteGuardado.getDomicilio().getCalle());
        Assertions.assertEquals(24L, pacienteGuardado.getDomicilio().getNumero());
        Assertions.assertEquals("Transilvania", pacienteGuardado.getDomicilio().getLocalidad());
        Assertions.assertEquals("Brasov", pacienteGuardado.getDomicilio().getProvincia());
        Assertions.assertEquals("hola@soydracula.com", pacienteGuardado.getEmail());
    }

    /***** TEST PARA BUSCAR PACIENTE POR EMAIL *****/
    @Test
    void buscarPacientePorEmail() {
        // DADO
        String email = paciente.getEmail();

        // CUANDO
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(email);

        // ENTONCES
        Assertions.assertTrue(pacienteBuscado.isPresent());
        Assertions.assertEquals(email, pacienteBuscado.get().getEmail());
        Assertions.assertEquals("Maria", pacienteBuscado.get().getNombre());
        Assertions.assertEquals("Lopez", pacienteBuscado.get().getApellido());
    }

    /***** TEST PARA ACTUALIZAR UN PACIENTE *****/
    @Test
    void actualizarPaciente() {
        // DADO
        paciente.setNombre("Maria");
        paciente.setApellido("Lopez");

        // CUANDO
        pacienteService.actualizarPaciente(paciente);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPacientePorId(paciente.getId());

        // ENTONCES
        Assertions.assertTrue(pacienteActualizado.isPresent());
        Assertions.assertEquals("Maria", pacienteActualizado.get().getNombre());
        Assertions.assertEquals("Lopez", pacienteActualizado.get().getApellido());
    }

    /***** TEST PARA LISTAR TODOS LOS PACIENTES *****/
    @Test
    void listarTodosLosPacientes() {
        // DADO - Pacientes ya creados en setUp

        // CUANDO
        List<Paciente> pacientes = pacienteService.buscarPacientes();

        // ENTONCES
        Assertions.assertFalse(pacientes.isEmpty());
        Assertions.assertTrue(pacientes.contains(paciente));
    }

    /***** TEST PARA AGREGAR ODONTOLOGO *****/
    @Test
    public void agregarOdontologo() {
        // DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Colmillos");
        odontologo.setNumeroMatricula(1212L);

        // CUANDO
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

        // ENTONCES
        Assertions.assertNotNull(odontologoGuardado);
        Assertions.assertNotNull(odontologoGuardado.getId());
        Assertions.assertEquals("Juan", odontologoGuardado.getNombre());
        Assertions.assertEquals("Colmillos", odontologoGuardado.getApellido());
        Assertions.assertEquals(1212L, odontologoGuardado.getNumeroMatricula());
    }

    /***** TEST PARA ACTUALIZAR UN ODONTOLOGO *****/
    @Test
    void actualizarOdontologo() {

        // DADO
        odontologo.setNombre("Carlos");
        odontologo.setApellido("Dientes");
        odontologo.setNumeroMatricula(959595L);

        // CUANDO
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoActualizado = odontologoService.buscarOdontologoPorId(odontologo.getId());

        // ENTONCES
        Assertions.assertTrue(odontologoActualizado.isPresent());
        Assertions.assertEquals("Carlos", odontologoActualizado.get().getNombre());
        Assertions.assertEquals("Dientes", odontologoActualizado.get().getApellido());
        Assertions.assertEquals(959595L, odontologoActualizado.get().getNumeroMatricula());
    }

    /***** TEST PARA BUSCAR ODONTOLOGO POR MATRICULA *****/
    @Test
    void buscarOdontologoPorMatricula() {

        // DADO
        Long numeroMatricula = odontologo.getNumeroMatricula();

        // CUANDO
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorMatricula(numeroMatricula);

        // ENTONCES
        Assertions.assertTrue(odontologoBuscado.isPresent());
        Assertions.assertEquals(numeroMatricula, odontologoBuscado.get().getNumeroMatricula());
        Assertions.assertEquals("Pedro", odontologoBuscado.get().getNombre());
        Assertions.assertEquals("Gomez", odontologoBuscado.get().getApellido());
    }

    @Test
    public void registrarTurno() {
        // DADO
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.of(2023, 6, 15));
        turno.setHora(LocalTime.of(10, 30));

        // CUANDO
        TurnoDTO turnoDTO = turnoService.registrarTurno(turno);

        // ENTONCES
        Assertions.assertNotNull(turnoDTO);
        Assertions.assertNotNull(turnoDTO.getId());
        Assertions.assertEquals(paciente.getId(), turnoDTO.getPacienteId());
        Assertions.assertEquals(odontologo.getId(), turnoDTO.getOdontologoId());
        Assertions.assertEquals(turno.getFecha(), turnoDTO.getFecha());
        Assertions.assertEquals(turno.getHora(), turnoDTO.getHora());
    }

}
