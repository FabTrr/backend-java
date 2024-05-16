import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.OdontologoService;
import dao.BD;
import model.Odontologo;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Before
    public void setUp() {
        BD.crearTablas();
        odontologoService = new OdontologoService();
    }

    @After
    public void tearDown() {
        BD.crearTablas();
    }

    @Test
    public void testListarTodosLosOdontologos() {
        Odontologo odontologo1 = new Odontologo(null, "12345", "Martinez", "Luis");
        Odontologo odontologo2 = new Odontologo(null, "67890", "Fernandez", "Carlos");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);


        List<Odontologo> odontologos = odontologoService.listarOdontologos();

        assertNotNull(odontologos);
        assertEquals(2, odontologos.size());
    }
}
