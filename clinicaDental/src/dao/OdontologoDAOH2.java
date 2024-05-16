package dao;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        try (Connection connection = BD.getConnection();
             PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            psInsert.setString(1, odontologo.getNumeroMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            if (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }
            logger.info("Odontologo guardado con exito");
        } catch (Exception e) {
            logger.error("Error al guardar el odontologo", e);
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorID(Integer id) {
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        return List.of();
    }

    @Override
    public Odontologo buscar(int id) {
        Odontologo odontologo = null;
        try (Connection connection = BD.getConnection();
             PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            psSelect.setInt(1, id);
            ResultSet rs = psSelect.executeQuery();
            if (rs.next()) {
                odontologo = new Odontologo(
                        rs.getInt("ID"),
                        rs.getString("NUMERO_MATRICULA"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO")
                );
            }
        } catch (Exception e) {
            logger.error("Error al buscar el odontologo", e);
        }
        return odontologo;
    }

    @Override
    public void eliminar(int id) {
        try (Connection connection = BD.getConnection();
             PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE)) {
            psDelete.setInt(1, id);
            psDelete.execute();
            logger.info("Odontologo eliminado con exito");
        } catch (Exception e) {
            logger.error("Error al eliminar el odontologo", e);
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        try (Connection connection = BD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            while (rs.next()) {
                Odontologo odontologo = new Odontologo(
                        rs.getInt("ID"),
                        rs.getString("NUMERO_MATRICULA"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO")
                );
                odontologos.add(odontologo);
            }
        } catch (SQLException e) {
            logger.error("Error al listar los odontologos", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return odontologos;
    }
}
