package mesa;

class AnalistaDeCalidad {
    private ControlCalidad controlLote;
    private ControlCalidad controlPeso;
    private ControlCalidad controlEnvasado;

    public AnalistaDeCalidad() {
        this.controlLote = new ControlLote();
        this.controlPeso = new ControlPeso();
        this.controlEnvasado = new ControlEnvasado();
    }

    public void validarCalidadDelProducto(Articulo articulo) {
        if (controlLote.verificar(articulo) && controlPeso.verificar(articulo) && controlEnvasado.verificar(articulo)) {
            System.out.println("El artículo " + articulo.getNombre() + " ha sido aceptado.");
        } else {
            System.out.println("El artículo " + articulo.getNombre() + " ha sido rechazado.");
        }
    }
}