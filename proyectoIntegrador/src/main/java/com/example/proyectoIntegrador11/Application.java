package com.example.proyectoIntegrador11;
import com.example.proyectoIntegrador11.entity.Paciente;
import com.example.proyectoIntegrador11.entity.Odontologo;
import com.example.proyectoIntegrador11.entity.Domicilio;
import com.example.proyectoIntegrador11.repository.BD;
import com.example.proyectoIntegrador11.service.OdontologoService;
import com.example.proyectoIntegrador11.service.PacienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		BD.crearTablas();
		Odontologo odontologo = new Odontologo(2145, "Juan", "Pablo");
		Odontologo odontologo2 = new Odontologo(789, "Laura", "Garcia");
		OdontologoService odontologoService = new OdontologoService();
		odontologoService.guardarOdontologo(odontologo);
		odontologoService.guardarOdontologo(odontologo2);
		Domicilio domicilio = new Domicilio("Zafiro", 2, "San Andres", "USA");
		Paciente paciente = new Paciente("Carla", "Alvarez", "123", Date.valueOf("2024-05-25"), domicilio, "carla@mail.com");
		PacienteService pacienteService = new PacienteService();
		Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
		SpringApplication.run(Application.class, args);
	}
}
