package mesa;
import java.util.HashMap;

// Clase para gestionar la creacion y almacenamiento de arboles
class ArbolFactory {
    private static HashMap<String, Arbol> arboles = new HashMap<>();

    // Metodo para obtener un arbol existente o crear uno nuevo
    public static Arbol obtenerArbol(String tipo) {
        Arbol arbol = arboles.get(tipo);

        if (arbol == null) {
            if (tipo.equals("ornamental")) {
                arbol = new Arbol(200, 400, "verde");
            } else if (tipo.equals("frutal")) {
                arbol = new Arbol(500, 300, "rojo");
            } else if (tipo.equals("floral")) {
                arbol = new Arbol(100, 200, "celeste");
            }
            arboles.put(tipo, arbol);
        }

        return arbol;
    }
}