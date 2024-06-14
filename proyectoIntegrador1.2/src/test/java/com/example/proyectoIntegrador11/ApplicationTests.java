package com.example.proyectoIntegrador11;

import com.example.proyectoIntegrador11.entity.Domicilio;
import com.example.proyectoIntegrador11.entity.Odontologo;
import com.example.proyectoIntegrador11.entity.Paciente;
import com.example.proyectoIntegrador11.entity.Turno;
import com.example.proyectoIntegrador11.entity.TurnoDTO;
import com.example.proyectoIntegrador11.exception.BadRequestException;
import com.example.proyectoIntegrador11.service.OdontologoService;
import com.example.proyectoIntegrador11.service.PacienteService;
import com.example.proyectoIntegrador11.service.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback
class ApplicationTests {

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
		domicilio.setNumero(123);
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
		odontologo.setNumeroMatricula(87654321);

		paciente = pacienteService.guardarPaciente(paciente);
		odontologo = odontologoService.guardarOdontologo(odontologo);
	}

	/***** TEST PARA AGREGAR PACIENTE *****/
	@Test
	public void agregarPaciente() {
		//DADO
		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("Calle A");
		domicilio.setNumero(123);
		domicilio.setLocalidad("Springfield");
		domicilio.setProvincia("Provincia Falsa");

		Paciente paciente = new Paciente();
		paciente.setNombre("Maria");
		paciente.setApellido("Dientes");
		paciente.setCedula("121212");
		paciente.setFechaIngreso(LocalDate.of(2023, 1, 1));
		paciente.setDomicilio(domicilio);
		paciente.setEmail("hola@maria.com");

		//CUANDO
		Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);

		//ENTONCES
		Assertions.assertNotNull(pacienteGuardado);
		Assertions.assertNotNull(pacienteGuardado.getId());
		Assertions.assertEquals("Maria", pacienteGuardado.getNombre());
		Assertions.assertEquals("Dientes", pacienteGuardado.getApellido());
		Assertions.assertEquals("121212", pacienteGuardado.getCedula());
		Assertions.assertEquals(LocalDate.of(2023, 1, 1), pacienteGuardado.getFechaIngreso());
		Assertions.assertNotNull(pacienteGuardado.getDomicilio());
		Assertions.assertEquals("Calle A", pacienteGuardado.getDomicilio().getCalle());
		Assertions.assertEquals(123, pacienteGuardado.getDomicilio().getNumero());
		Assertions.assertEquals("Springfield", pacienteGuardado.getDomicilio().getLocalidad());
		Assertions.assertEquals("Provincia Falsa", pacienteGuardado.getDomicilio().getProvincia());
		Assertions.assertEquals("hola@maria.com", pacienteGuardado.getEmail());
	}

	/***** TEST PARA BUSCAR PACIENTE POR EMAIL *****/
	@Test
	void buscarPacientePorEmail() {
		//DADO
		String email = paciente.getEmail();

		//CUANDO
		Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(email);

		//ENTONCES
		Assertions.assertTrue(pacienteBuscado.isPresent());
		Assertions.assertEquals(email, pacienteBuscado.get().getEmail());
		Assertions.assertEquals("Maria", pacienteBuscado.get().getNombre());
		Assertions.assertEquals("Lopez", pacienteBuscado.get().getApellido());
	}

	/***** TEST PARA ACTUALIZAR UN PACIENTE *****/
	@Test
	void actualizarPaciente() {
		//DADO
		paciente.setNombre("María Actualizada");
		paciente.setApellido("Lopez Actualizada");

		//CUANDO
		pacienteService.actualizarPaciente(paciente);
		Optional<Paciente> pacienteActualizado = pacienteService.buscarPacientePorId(paciente.getId());

		//ENTONCES
		Assertions.assertTrue(pacienteActualizado.isPresent());
		Assertions.assertEquals("María Actualizada", pacienteActualizado.get().getNombre());
		Assertions.assertEquals("Lopez Actualizada", pacienteActualizado.get().getApellido());
	}

	/***** TEST PARA LISTAR TODOS LOS PACIENTES *****/
	@Test
	void listarTodosLosPacientes() {
		//DADO - Pacientes ya creados en setUp

		//CUANDO
		List<Paciente> pacientes = pacienteService.buscarPacientes();

		//ENTONCES
		Assertions.assertFalse(pacientes.isEmpty());
		Assertions.assertTrue(pacientes.contains(paciente));
	}

	/***** TEST PARA AGREGAR ODONTOLOGO *****/
	@Test
	public void agregarOdontologo() {
		//DADO
		Odontologo odontologo = new Odontologo();
		odontologo.setNombre("Juan");
		odontologo.setApellido("Muelas");
		odontologo.setNumeroMatricula(1212);

		//CUANDO
		Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

		//ENTONCES
		Assertions.assertNotNull(odontologoGuardado);
		Assertions.assertNotNull(odontologoGuardado.getId());
		Assertions.assertEquals("Juan", odontologoGuardado.getNombre());
		Assertions.assertEquals("Muelas", odontologoGuardado.getApellido());
		Assertions.assertEquals(1212, odontologoGuardado.getNumeroMatricula());
	}

	/***** TEST PARA BUSCAR ODONTOLOGO POR MATRICULA *****/
	@Test
	void buscarOdontologoPorMatricula() {
		//DADO
		Integer numeroMatricula = odontologo.getNumeroMatricula();

		//CUANDO
		Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorMatricula(numeroMatricula);

		//ENTONCES
		Assertions.assertTrue(odontologoBuscado.isPresent());
		Assertions.assertEquals(numeroMatricula, odontologoBuscado.get().getNumeroMatricula());
		Assertions.assertEquals("Pedro", odontologoBuscado.get().getNombre());
		Assertions.assertEquals("Gomez", odontologoBuscado.get().getApellido());
	}

	/***** TEST PARA GUARDAR TURNO *****/
	@Test
	void guardarTurno() {
		//DADO
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setPaciente(paciente);
		turnoDTO.setOdontologo(odontologo);
		turnoDTO.setFecha(LocalDate.of(2023, 6, 15));

		//CUANDO
		Turno turnoGuardado = turnoService.guardarTurno(turnoDTO);

		//ENTONCES
		Assertions.assertNotNull(turnoGuardado);
		Assertions.assertNotNull(turnoGuardado.getId());
		Assertions.assertEquals(paciente.getId(), turnoGuardado.getPaciente().getId());
		Assertions.assertEquals(odontologo.getId(), turnoGuardado.getOdontologo().getId());
	}

	/***** TEST PARA BUSCAR TURNO POR ID *****/
	@Test
	void buscarTurnoPorId() {
		//DADO
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setPaciente(paciente);
		turnoDTO.setOdontologo(odontologo);
		turnoDTO.setFecha(LocalDate.of(2023, 6, 15));
		Turno turnoGuardado = turnoService.guardarTurno(turnoDTO);

		//CUANDO
		Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoPorId(turnoGuardado.getId());

		//ENTONCES
		Assertions.assertTrue(turnoBuscado.isPresent());
		Assertions.assertEquals(turnoGuardado.getId(), turnoBuscado.get().getId());
	}

	/***** TEST PARA ACTUALIZAR TURNO *****/
	@Test
	void actualizarTurno() throws BadRequestException {
		//DADO
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setPaciente(paciente);
		turnoDTO.setOdontologo(odontologo);
		turnoDTO.setFecha(LocalDate.of(2023, 6, 15));
		Turno turnoGuardado = turnoService.guardarTurno(turnoDTO);

		turnoDTO.setId(turnoGuardado.getId());
		turnoDTO.setFecha(LocalDate.of(2023, 6, 16));

		//CUANDO
		turnoService.actualizarTurno(turnoDTO);
		Optional<TurnoDTO> turnoActualizado = turnoService.buscarTurnoPorId(turnoGuardado.getId());

		//ENTONCES
		Assertions.assertTrue(turnoActualizado.isPresent());
		Assertions.assertEquals(LocalDate.of(2023, 6, 16), turnoActualizado.get().getFecha());
	}

	/***** TESTS DE ELIMINACIÓN *****/

	/***** TEST PARA ELIMINAR PACIENTE *****/
	@Test
	void eliminarPaciente() {
		//DADO
		Integer pacienteId = paciente.getId();

		//CUANDO
		pacienteService.eliminarPaciente(pacienteId);
		Optional<Paciente> pacienteEliminado = pacienteService.buscarPacientePorId(pacienteId);

		//ENTONCES
		Assertions.assertFalse(pacienteEliminado.isPresent());
	}

	/***** TEST PARA ELIMINAR ODONTÓLOGO *****/
	@Test
	void eliminarOdontologo() {
		//DADO
		Integer odontologoId = odontologo.getId();

		//CUANDO
		odontologoService.eliminarOdontologo(odontologoId);
		Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologoPorId(odontologoId);

		//ENTONCES
		Assertions.assertFalse(odontologoEliminado.isPresent());
	}

	/***** TEST PARA ELIMINAR TURNO *****/
	@Test
	void eliminarTurno() {
		//DADO
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setPaciente(paciente);
		turnoDTO.setOdontologo(odontologo);
		turnoDTO.setFecha(LocalDate.of(2023, 6, 15));
		Turno turnoGuardado = turnoService.guardarTurno(turnoDTO);

		//CUANDO
		turnoService.eliminarTurno(turnoGuardado.getId());
		Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurnoPorId(turnoGuardado.getId());

		//ENTONCES
		Assertions.assertFalse(turnoEliminado.isPresent());
	}

}
