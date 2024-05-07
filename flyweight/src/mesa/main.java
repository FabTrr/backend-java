package mesa;

public class main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        // Crear 500,000 arboles rojos
        for (int i = 0; i < 500000; i++) {
            Arbol arbolRojo = ArbolFactory.obtenerArbol("frutal");
        }

        System.out.println("Memoria usada despues de crear 500,000 arboles rojos: " +
                (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");

        // Crear 500,000 arboles verdes
        for (int i = 0; i < 500000; i++) {
            Arbol arbolVerde = ArbolFactory.obtenerArbol("ornamental");
        }

        System.out.println("Memoria usada despues de crear 1,000,000 arboles (500,000 rojos y 500,000 verdes): " +
                (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");
    }
}