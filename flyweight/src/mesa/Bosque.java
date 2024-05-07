package mesa;

public class Bosque {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        // Crear 500000 arboles rojos
        for (int i = 0; i < 500000; i++) {
            Arbol arbolRojo = ArbolFactory.obtenerArbol("frutal");
        }

        System.out.println("Memoria usada despues de crear 500000 arboles rojos: " +
                (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");

        // Crear 500000 arboles verdes
        for (int i = 0; i < 500000; i++) {
            Arbol arbolVerde = ArbolFactory.obtenerArbol("ornamental");
        }

        System.out.println("Memoria usada despues de crear 1000000 arboles (500000 rojos y 500000 verdes): " +
                (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");
    }
}