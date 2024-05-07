package mesa;

// Clase para representar un arbol
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

    // Metodo para obtener la altura del arbol
    public int obtenerAlto() {
        return alto;
    }

    // Metodo para obtener el ancho del arbol
    public int obtenerAncho() {
        return ancho;
    }

    // Metodo para obtener el color del arbol
    public String obtenerColor() {
        return color;
    }
}