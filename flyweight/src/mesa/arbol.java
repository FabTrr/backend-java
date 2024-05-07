package mesa;

// Clase para representar un árbol
class Arbol {
    private int alto;
    private int ancho;
    private String color;

    // Constructor
    public Arbol(int alto, int ancho, String color) {
        this.alto = alto;
        this.ancho = ancho;
        this.color = color;
    }

    // Método para obtener la altura del árbol
    public int obtenerAlto() {
        return alto;
    }

    // Método para obtener el ancho del árbol
    public int obtenerAncho() {
        return ancho;
    }

    // Método para obtener el color del árbol
    public String obtenerColor() {
        return color;
    }
}