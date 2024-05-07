//fachada que interactua con busqueda de vuelos y hoteles

class AgenciaViajesFacade {
    private SistemaBusquedaVuelo sistemaBusquedaVuelo;
    private SistemaBusquedaHotel sistemaBusquedaHotel;

    //constructor de la clase crea instancias
    public AgenciaViajesFacade() {
        this.sistemaBusquedaVuelo = new SistemaBusquedaVuelo();
        this.sistemaBusquedaHotel = new SistemaBusquedaHotel();
    }

    //interfaz que unifica vuelos y hoteles
    public void buscarVueloYHotel(String fecha, String ciudad) {
        sistemaBusquedaVuelo.buscarVuelo(fecha, fecha, "Origen", ciudad);
        sistemaBusquedaHotel.buscarHotel(fecha, fecha, ciudad);
    }
}