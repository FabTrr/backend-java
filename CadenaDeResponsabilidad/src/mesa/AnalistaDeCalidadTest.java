import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnalistaDeCalidadTest {

    @Test
    public void testValidarCalidadDelProducto_Aceptado() {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo = new Articulo("Articulo1", 1500, 1250, "sano");
        String resultadoEsperado = "El artículo Articulo1 ha sido aceptado.";
        assertEquals(resultadoEsperado, analista.validarCalidadDelProducto(articulo));
    }

    @Test
    public void testValidarCalidadDelProducto_LoteIncorrecto() {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo = new Articulo("Articulo2", 500, 1250, "sano");
        String resultadoEsperado = "El artículo Articulo2 ha sido rechazado.";
        assertEquals(resultadoEsperado, analista.validarCalidadDelProducto(articulo));
    }

    @Test
    public void testValidarCalidadDelProducto_PesoIncorrecto() {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo = new Articulo("Articulo3", 1500, 1400, "sano");
        String resultadoEsperado = "El artículo Articulo3 ha sido rechazado.";
        assertEquals(resultadoEsperado, analista.validarCalidadDelProducto(articulo));
    }

    @Test
    public void testValidarCalidadDelProducto_EnvasadoIncorrecto() {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo = new Articulo("Articulo4", 1500, 1250, "deteriorado");
        String resultadoEsperado = "El artículo Articulo4 ha sido rechazado.";
        assertEquals(resultadoEsperado, analista.validarCalidadDelProducto(articulo));
    }

    @Test
    public void testValidarCalidadDelProducto_CumpleLotePeroNoPesoNiEnvasado() {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo = new Articulo("Articulo5", 1800, 1400, "deteriorado");
        String resultadoEsperado = "El artículo Articulo5 ha sido rechazado.";
        assertEquals(resultadoEsperado, analista.validarCalidadDelProducto(articulo));
    }

    @Test
    public void testValidarCalidadDelProducto_CumpleLotePeroNoEnvasado() {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo = new Articulo("Articulo6", 1500, 1250, "deteriorado");
        String resultadoEsperado = "El artículo Articulo6 ha sido rechazado.";
        assertEquals(resultadoEsperado, analista.validarCalidadDelProducto(articulo));
    }

    @Test
    public void testValidarCalidadDelProducto_CumpleLotePeroNoPeso() {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo = new Articulo("Articulo7", 1500, 1350, "sano");
        String resultadoEsperado = "El artículo Articulo7 ha sido rechazado.";
        assertEquals(resultadoEsperado, analista.validarCalidadDelProducto(articulo));
    }
}