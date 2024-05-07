package mesa;

class ControlEnvasado implements ControlCalidad {
    @Override
    public boolean verificar(Articulo articulo) {
        String envasado = articulo.getEnvasado();
        return envasado.equals("sano") || envasado.equals("casi sano");
    }
}