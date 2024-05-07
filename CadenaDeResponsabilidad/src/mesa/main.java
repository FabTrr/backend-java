package mesa;

public class main {
    public static void main(String[] args) {
        AnalistaDeCalidad analista = new AnalistaDeCalidad();

        Articulo articulo1 = new Articulo("Articulo1", 1500, 1250, "sano");
        analista.validarCalidadDelProducto(articulo1);

        Articulo articulo2 = new Articulo("Articulo2", 3000, 1350, "casi sano");
        analista.validarCalidadDelProducto(articulo2);
    }
}