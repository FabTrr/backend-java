import org.junit.Test;
import static org.junit.Assert.*;

public class SistemaBusquedaVueloTest {

    @Test
    public void testBuscarVuelo() {
        SistemaBusquedaVuelo sistema = new SistemaBusquedaVuelo();

        // Supongamos que queremos buscar vuelos de Madrid a Paris para el 2024-05-10 al 2024-05-17
        String fechaSalida = "2024-05-10";
        String fechaRegreso = "2024-05-17";
        String origen = "Madrid";
        String destino = "Paris";

        // Ejecutamos el metodo de busqueda de vuelo
        sistema.buscarVuelo(fechaSalida, fechaRegreso, origen, destino);

    }
}