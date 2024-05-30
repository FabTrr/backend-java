package com.example.proyectoIntegrador11.service;

import com.example.proyectoIntegrador11.repository.TurnosDAOLISTA;
import com.example.proyectoIntegrador11.repository.iDao;
import com.example.proyectoIntegrador11.entity.Turno;

import java.util.List;

public class TurnoService {
    private iDao<Turno> turnoiDao;

    public TurnoService() {
        turnoiDao = new TurnosDAOLISTA();
    }

    public Turno guardarTurno(Turno turno) {
        return turnoiDao.guardar(turno);
    }

    public Turno buscarPorID(Integer id) {
        return turnoiDao.buscarPorID(id);
    }

    public List<Turno> listarTurnos() {
        return turnoiDao.buscarTodos();
    }

    public void eliminarTurno(Integer id) {
        turnoiDao.eliminar(id);
    }

    public void actualizarTurno(Turno turno) {
        turnoiDao.actualizar(turno);
    }
}