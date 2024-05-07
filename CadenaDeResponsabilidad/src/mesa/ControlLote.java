package mesa;

class ControlLote implements ControlCalidad {
    @Override
    public boolean verificar(Articulo articulo) {
        int lote = articulo.getLote();
        return lote >= 1000 && lote <= 2000;
    }
}