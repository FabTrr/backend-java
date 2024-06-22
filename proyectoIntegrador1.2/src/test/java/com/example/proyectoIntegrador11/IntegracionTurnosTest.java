package com.example.proyectoIntegrador11;

import com.example.proyectoIntegrador11.entity.*;
import com.example.proyectoIntegrador11.service.OdontologoService;
import com.example.proyectoIntegrador11.service.PacienteService;
import com.example.proyectoIntegrador11.service.TurnoService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDatos(){
        Paciente pacienteGuardado= pacienteService.guardarPaciente(new Paciente("Jorgito","Pereyra","111111", LocalDate.of(2024,6,19),new Domicilio("Calle falsa", 123L,"La Rioja","Argentina"),"jorgito@digitalhouse.com"));
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(new Odontologo(125L,"Ivan","Bustamante"));
        TurnoDTO turnoGuardado = turnoService.registrarTurno(new Turno(pacienteGuardado, odontologoGuardado, LocalDate.of(2024, 6, 19)));
        Paciente pacienteGuardado2= pacienteService.guardarPaciente(new Paciente("Daniela","Martinez","222222", LocalDate.of(2024,8,25),new Domicilio("Calle falsa2", 12L,"La Paz","Bolivia"),"daniela@digitalhouse.com"));
        TurnoDTO turnoGuardado2= turnoService.registrarTurno(new Turno(pacienteGuardado2,odontologoGuardado,LocalDate.of(2024,12,10)));
    }

    @Test
    public void eliminarTurno() throws Exception {
        Long idTurnoExistente = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/" + idTurnoExistente))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Turno eliminado"));

        mockMvc.perform(MockMvcRequestBuilders.get("/turnos/" + idTurnoExistente))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void listarTodosLosTurnos() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}