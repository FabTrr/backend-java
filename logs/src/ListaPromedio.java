import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListaPromedio {
    private static final Logger logger = LogManager.getLogger(ListaPromedio.class);

    private List<Integer> listaEnteros;

    public ListaPromedio(List<Integer> listaEnteros) throws Exception {
        this.listaEnteros = listaEnteros;
        if (listaEnteros.isEmpty()) {
            logger.error("La lista es igual a cero");
            throw new Exception("La lista es igual a cero");
        }
        if (listaEnteros.size() > 5) {
            logger.info("La longitud de la lista es mayor a 5");
        }
        if (listaEnteros.size() > 10) {
            logger.info("La longitud de la lista es mayor a 10");
        }
    }

    public double calcularPromedio() {
        return listaEnteros.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public int calcularMaximo() {
        return listaEnteros.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    public int calcularMinimo() {
        return listaEnteros.stream().mapToInt(Integer::intValue).min().orElse(0);
    }
}
