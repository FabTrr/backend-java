import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
    private static final Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        try {
            // Crear una lista de enteros
            List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            // Crear instancia de ListaPromedio
            ListaPromedio listaPromedio = new ListaPromedio(lista);

            // Calcular promedio, máximo y mínimo
            double promedio = listaPromedio.calcularPromedio();
            int maximo = listaPromedio.calcularMaximo();
            int minimo = listaPromedio.calcularMinimo();

            // Imprimir resultados
            logger.info("Promedio: " + promedio);
            logger.info("Máximo: " + maximo);
            logger.info("Mínimo: " + minimo);
        } catch (Exception e) {
            logger.error("Error al crear la lista: " + e.getMessage());
        }
    }
}
