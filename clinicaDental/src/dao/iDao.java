package dao;

import model.Odontologo;

import java.util.List;

public interface iDao<T> {
    T guardar(T t);
    T buscarPorID(Integer id);
    void actualizar(T t);
    void eliminar(Integer id);
    List<T> buscarTodos();

    Odontologo buscar(int id);

    void eliminar(int id);

    List<Odontologo> listarTodos();
}