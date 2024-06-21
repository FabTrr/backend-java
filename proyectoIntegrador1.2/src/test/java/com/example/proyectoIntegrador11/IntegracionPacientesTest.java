package com.example.proyectoIntegrador11;

import com.example.proyectoIntegrador11.service.PacienteService;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionPacientesTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void guardarPaciente() throws Exception {
        String pacienteJson = "{ \"nombre\": \"Maria\", \"apellido\": \"Lopez\", \"cedula\": \"121212\", \"fechaIngreso\": \"2023-01-01\", \"email\": \"hola@maria.com\", \"domicilio\": { \"calle\": \"Calle Falsa\", \"numero\": 123, \"localidad\": \"Springfield\", \"provincia\": \"Provincia Falsa\" } }";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(respuesta.getResponse().getContentAsString().contains("Maria"));
        assertTrue(respuesta.getResponse().getContentAsString().contains("Lopez"));
        assertTrue(respuesta.getResponse().getContentAsString().contains("121212"));
        assertTrue(respuesta.getResponse().getContentAsString().contains("hola@maria.com"));
        assertTrue(respuesta.getResponse().getContentAsString().contains("Calle Falsa"));
        assertTrue(respuesta.getResponse().getContentAsString().contains("123"));
        assertTrue(respuesta.getResponse().getContentAsString().contains("Springfield"));
        assertTrue(respuesta.getResponse().getContentAsString().contains("Provincia Falsa"));
    }

    @Test
    public void listarTodosLosPacientes() throws Exception {
        String pacienteJson = "{ \"nombre\": \"Maria\", \"apellido\": \"Lopez\", \"cedula\": \"121212\", \"fechaIngreso\": \"2023-01-01\", \"email\": \"hola@hola.com\", \"domicilio\": { \"calle\": \"Calle Falsa\", \"numero\": 123, \"localidad\": \"Springfield\", \"provincia\": \"Provincia Falsa\" } }";
        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(respuesta.getResponse().getContentAsString().contains("Maria"));
    }

    @Test
    public void buscarPacientePorId() throws Exception {
        String pacienteJson = "{ \"nombre\": \"Maria\", \"apellido\": \"Lopez\", \"cedula\": \"121212\", \"fechaIngreso\": \"2023-01-01\", \"email\": \"mail@maria.com\", \"domicilio\": { \"calle\": \"Calle Falsa\", \"numero\": 123, \"localidad\": \"Springfield\", \"provincia\": \"Provincia Falsa\" } }";
        MvcResult resultadoGuardar = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String respuestaGuardar = resultadoGuardar.getResponse().getContentAsString();
        Long pacienteId = JsonPath.parse(respuestaGuardar).read("$.id", Long.class);

        MvcResult resultadoBuscar = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/" + pacienteId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(resultadoBuscar.getResponse().getContentAsString().contains("Maria"));
    }

    @Test
    public void actualizarPaciente() throws Exception {
        String pacienteJson = "{ \"nombre\": \"Maria\", \"apellido\": \"Lopez\", \"cedula\": \"121212\", \"fechaIngreso\": \"2023-01-01\", \"email\": \"hola@maria.com\", \"domicilio\": { \"calle\": \"Calle Falsa\", \"numero\": 123, \"localidad\": \"Springfield\", \"provincia\": \"Provincia Falsa\" } }";
        MvcResult resultadoGuardar = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String respuestaGuardar = resultadoGuardar.getResponse().getContentAsString();
        Long pacienteId = JsonPath.parse(respuestaGuardar).read("$.id", Long.class);

        String pacienteActualizadoJson = "{ \"id\": " + pacienteId + ", \"nombre\": \"Juana\", \"apellido\": \"Lopez\", \"cedula\": \"121212\", \"fechaIngreso\": \"2023-01-01\", \"email\": \"hola@juana.com\", \"domicilio\": { \"calle\": \"Calle Falsa\", \"numero\": 123, \"localidad\": \"Springfield\", \"provincia\": \"Provincia Falsa\" } }";
        mockMvc.perform(MockMvcRequestBuilders.put("/pacientes/actualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteActualizadoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult resultadoBuscar = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/" + pacienteId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(resultadoBuscar.getResponse().getContentAsString().contains("Juana"));
    }

    @Test
    public void eliminarPaciente() throws Exception {
        String pacienteJson = "{ \"nombre\": \"Maria\", \"apellido\": \"Lopez\", \"cedula\": \"121212\", \"fechaIngreso\": \"2023-01-01\", \"email\": \"chau@maria.com\", \"domicilio\": { \"calle\": \"Calle Falsa\", \"numero\": 123, \"localidad\": \"Springfield\", \"provincia\": \"Provincia Falsa\" } }";
        MvcResult resultadoGuardar = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String respuestaGuardar = resultadoGuardar.getResponse().getContentAsString();
        Long pacienteId = JsonPath.parse(respuestaGuardar).read("$.id", Long.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/" + pacienteId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult resultadoBuscar = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/" + pacienteId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertTrue(resultadoBuscar.getResponse().getContentAsString().isEmpty());
    }


}
