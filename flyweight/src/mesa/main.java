package mesa;

public class main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        // Crear 500,000 árboles rojos
        for (int i = 0; i < 500000; i++) {
            Arbol arbolRojo = ArbolFactory.obtenerArbol("frutal");
        }

        System.out.println("Memoria usada después de crear 500,000 árboles rojos: " +
                (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");

        // Crear 500,000 árboles verdes
        for (int i = 0; i < 500000; i++) {
            Arbol arbolVerde = ArbolFactory.obtenerArbol("ornamental");
        }

        System.out.println("Memoria usada después de crear 1,000,000 árboles (500,000 rojos y 500,000 verdes): " +
                (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");
    }
}