package com.example.proyectoIntegrador11;

import com.example.proyectoIntegrador11.entity.*;
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

/***** >>> TESTS DEL 1 AL 4 PRUEBAS EN PACIENTES *****/
/***** >>> TESTS DEL 5 AL 8 PRUEBAS EN ODONTOLOGOS *****/
/***** >>> TESTS DEL 9 AL 11 PRUEBAS EN TURNOS *****/
/***** >>> TESTS DEL 12 AL 14 TAREAS DE ELIMINACION *****/

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

    /***** CARGA DE DATOS *****/

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
    @Order(1)
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
    @Order(2)
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
    @Order(3)
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
    @Order(4)
    void listarTodosLosPacientes() {
        // DADO - Pacientes ya creados en setUp

        // CUANDO
        List<Paciente> pacientes = pacienteService.buscarPacientes();

        // ENTONCES
        Assertions.assertFalse(pacientes.isEmpty());
        Assertions.assertTrue(pacientes.contains(paciente));
    }

    /***** TEST PARA ELIMINAR PACIENTE *****/
    @Test
    @Order(14)
    void eliminarPaciente() {
        // DADO - paciente ya guardado en setup

        // CUANDO
        pacienteService.eliminarPaciente(paciente.getId());

        // ENTONCES
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPacientePorId(paciente.getId());
        Assertions.assertFalse(pacienteEliminado.isPresent(), "El paciente no debería existir después de eliminarlo");
    }

    /***** TEST PARA AGREGAR ODONTOLOGO *****/
    @Test
    @Order(5)
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
    @Order(6)
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
    @Order(7)
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

    /***** TEST PARA LISTAR ODONTOLOGOS *****/

    @Test
    @Order(8)
    void listarOdontologos() {
        // CUANDO
        List<Odontologo> odontologos = odontologoService.buscarOdontologos();

        // ENTONCES
        Assertions.assertFalse(odontologos.isEmpty());
        Assertions.assertTrue(odontologos.contains(odontologo));
    }

    /***** TEST PARA ELIMINAR ODONTOLOGO *****/

    @Test
    @Order(13)
    void eliminarOdontologo() {
        // DADO - odontologo ya guardado en setup

        // CUANDO
        odontologoService.eliminarOdontologo(odontologo.getId());

        // ENTONCES
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologoPorId(odontologo.getId());
        Assertions.assertFalse(odontologoEliminado.isPresent(), "El odontólogo no existe");
    }

    /***** TEST PARA AGREGAR TURNO *****/

    @Test
    @Order(9)
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

    /***** TEST PARA BUSCAR TURNO POR ID *****/

    @Test
    @Order(10)
    public void buscarTurnoPorId() {
        // DADO
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.of(2023, 6, 15));
        turno.setHora(LocalTime.of(10, 30));

        TurnoDTO turnoDTO = turnoService.registrarTurno(turno);

        // CUANDO
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoId(turnoDTO.getId());

        // ENTONCES
        Assertions.assertTrue(turnoBuscado.isPresent());
        Assertions.assertEquals(turnoDTO.getId(), turnoBuscado.get().getId());
        Assertions.assertEquals(turno.getFecha(), turnoBuscado.get().getFecha());
        Assertions.assertEquals(turno.getHora(), turnoBuscado.get().getHora());
        Assertions.assertEquals(turno.getPaciente().getId(), turnoBuscado.get().getPacienteId());
        Assertions.assertEquals(turno.getOdontologo().getId(), turnoBuscado.get().getOdontologoId());
    }

    /***** TEST PARA ACTUALIZAR TURNO *****/

    @Test
    @Order(11)
    public void actualizarTurno() {
        // DADO
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.of(2023, 6, 15));
        turno.setHora(LocalTime.of(10, 30));

        TurnoDTO turnoDTO = turnoService.registrarTurno(turno);

        // aqui actualizamos fecha y hora del turno
        turnoDTO.setFecha(LocalDate.of(2023, 7, 20));
        turnoDTO.setHora(LocalTime.of(11, 0));

        // CUANDO
        turnoService.actualizarTurno(turnoDTO);

        // ENTONCES
        Optional<TurnoDTO> turnoActualizado = turnoService.buscarTurnoId(turnoDTO.getId());
        Assertions.assertTrue(turnoActualizado.isPresent());
        Assertions.assertEquals(turnoDTO.getFecha(), turnoActualizado.get().getFecha());
        Assertions.assertEquals(turnoDTO.getHora(), turnoActualizado.get().getHora());
    }

    /***** TEST PARA ELIMINAR TURNO *****/

    @Test
    @Order(12)
    public void eliminarTurno() {
        // DADO
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.of(2023, 6, 15));
        turno.setHora(LocalTime.of(10, 30));

        TurnoDTO turnoDTO = turnoService.registrarTurno(turno);

        // CUANDO
        turnoService.eliminarTurno(turnoDTO.getId());

        // ENTONCES
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurnoId(turnoDTO.getId());
        Assertions.assertFalse(turnoEliminado.isPresent());
    }
}