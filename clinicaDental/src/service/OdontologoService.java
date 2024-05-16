package service;

import dao.OdontologoDAOH2;
import dao.iDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        this.odontologoiDao = new OdontologoDAOH2();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoiDao.guardar(odontologo);
    }

    public void eliminarOdontologo(int id) {
        odontologoiDao.eliminar(id);
    }

    public List<Odontologo> listarOdontologos() {
        return List.of();
    }
}
