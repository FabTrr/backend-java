package com.example.proyectoIntegrador11;

import com.example.proyectoIntegrador11.entity.Odontologo;
import com.example.proyectoIntegrador11.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void agregarOdontologo() {
        //DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Gomez");
        odontologo.setNumeroMatricula(87654321);

        //CUANDO
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

        //ENTONCES
        Assertions.assertNotNull(odontologoGuardado);
        Assertions.assertNotNull(odontologoGuardado.getId());
        Assertions.assertEquals("Pedro", odontologoGuardado.getNombre());
        Assertions.assertEquals("Gomez", odontologoGuardado.getApellido());
        Assertions.assertEquals(87654321, odontologoGuardado.getNumeroMatricula());
    }
}
