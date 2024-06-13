package com.example.proyectoIntegrador11;

import com.example.proyectoIntegrador11.entity.Paciente;
import com.example.proyectoIntegrador11.entity.Domicilio;
import com.example.proyectoIntegrador11.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    public void agregarPaciente() {
        // DADO
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle Falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("Springfield");
        domicilio.setProvincia("Provincia Falsa");

        Paciente paciente = new Paciente();
        paciente.setNombre("Juan");
        paciente.setApellido("Perez");
        paciente.setCedula("12345678");
        paciente.setFechaIngreso(LocalDate.of(2023, 1, 1));
        paciente.setDomicilio(domicilio);
        paciente.setEmail("juan.perez@example.com");

        // CUANDO
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);

        // ENTONCES
        Assertions.assertNotNull(pacienteGuardado);
        Assertions.assertNotNull(pacienteGuardado.getId());
        Assertions.assertEquals("Juan", pacienteGuardado.getNombre());
        Assertions.assertEquals("Perez", pacienteGuardado.getApellido());
        Assertions.assertEquals("12345678", pacienteGuardado.getCedula());
        Assertions.assertEquals(LocalDate.of(2023, 1, 1), pacienteGuardado.getFechaIngreso());
        Assertions.assertNotNull(pacienteGuardado.getDomicilio());
        Assertions.assertEquals("Calle Falsa", pacienteGuardado.getDomicilio().getCalle());
        Assertions.assertEquals(123, pacienteGuardado.getDomicilio().getNumero());
        Assertions.assertEquals("Springfield", pacienteGuardado.getDomicilio().getLocalidad());
        Assertions.assertEquals("Provincia Falsa", pacienteGuardado.getDomicilio().getProvincia());
        Assertions.assertEquals("juan.perez@example.com", pacienteGuardado.getEmail());
    }
}
