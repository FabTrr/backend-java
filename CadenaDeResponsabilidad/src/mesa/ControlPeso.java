package mesa;

class ControlPeso implements ControlCalidad {
    @Override
    public boolean verificar(Articulo articulo) {
        int peso = articulo.getPeso();
        return peso >= 1200 && peso <= 1300;
    }
}