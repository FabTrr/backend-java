import model.Domicilio;
import model.Odontologo;
import model.Paciente;
import service.OdontologoService;
import service.PacienteService;
import dao.BD;

import java.time.LocalDate;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        BD.crearTablas();

        //Pacientes
        Domicilio domicilio1 = new Domicilio(null, "Calle Falsa", 123, "Springfield", "Springfield");
        Paciente paciente1 = new Paciente(null, "Perez", "Juan", "12345678", LocalDate.now(), domicilio1);

        Domicilio domicilio2 = new Domicilio(null, "Avenida Siempre Viva", 742, "Springfield", "Springfield");
        Paciente paciente2 = new Paciente(null, "Gomez", "Ana", "87654321", LocalDate.now(), domicilio2);

        PacienteService pacienteService = new PacienteService();
        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);

        Paciente pacienteBuscado = pacienteService.buscarPaciente(paciente1.getId());
        if (pacienteBuscado != null) {
            System.out.println("Paciente encontrado: " + pacienteBuscado.getNombre());
        } else {
            System.out.println("Paciente no encontrado");
        }


        //Odontologos
        Odontologo odontologo1 = new Odontologo(null, "12345", "Martinez", "Alicia");
        Odontologo odontologo2 = new Odontologo(null, "67890", "Gonzalez", "Alberto");

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
    }
}