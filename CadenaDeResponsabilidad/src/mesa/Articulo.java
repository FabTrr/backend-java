package mesa;

class Articulo {
    private String nombre;
    private int lote;
    private int peso;
    private String envasado;

    public Articulo(String nombre, int lote, int peso, String envasado) {
        this.nombre = nombre;
        this.lote = lote;
        this.peso = peso;
        this.envasado = envasado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLote() {
        return lote;
    }

    public int getPeso() {
        return peso;
    }

    public String getEnvasado() {
        return envasado;
    }
}