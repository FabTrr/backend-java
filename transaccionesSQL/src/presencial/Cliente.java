package presencial;

import org.apache.log4j.Logger;

import java.sql.*;

import static java.lang.Class.forName;

public class Cliente {
    //PreparatedStatement
    private static final String SQL_DROP_CREATE="DROP TABLE IF EXISTS CUENTAS; " +
            " CREATE TABLE CUENTAS (ID INT NOT NULL PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, NUMERO_CUENTA INT NOT NULL, SALDO NUMERIC(10,2) NOT NULL)";
    private static final String SQL_INSERT="INSERT INTO CUENTAS VALUES(?,?,?,?)";

    //placeholders (los signos de interrogación ?) se utilizan para asignar valores en tiempo de ejecucion

    private static final String SQL_UPDATE="UPDATE CUENTAS SET SALDO=? WHERE ID=?";

    //private: Accesible solo dentro de la clase en la que se define.
    //static: Compartida por todas las instancias de la clase, accesible sin crear una instancia de la clase.
    //final: Su valor no puede cambiar una vez que ha sido asignado.
    private static final String SQL_SELECT="SELECT * FROM CUENTAS";

    //logger
    private static final Logger logger= Logger.getLogger(Cliente.class);

    //conexion y statements
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection= getConnection();
            logger.info("Conexion Exitosa");
            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE);
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT);
            //primer signo (?) y su valor, en este caso el ID (Int)
            psInsert.setInt(1,1);
            //segundo signo (?) y su valor, en este caso el nombre (String)
            psInsert.setString(2,"Karen");
            //tercer signo (?) y su valor, en este caso el Numero de cuenta (Int)
            psInsert.setInt(3,10101010);
            //cuarto signo (?) y su valor, en este caso el Saldo (Double)
            psInsert.setDouble(4, 100);
            psInsert.execute(); //<-- aqui volvio el rollback
            logger.info("Saldo inicial: 100");

            //sumarle 10 al saldo
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            //primer parametro (?) "SET SALDO":
            psUpdate.setDouble(1,100+10);
            //segundo parametro (?) "WHERE ID":
            psUpdate.setInt(2,1);
            psUpdate.execute();
            logger.info("Saldo actualizado en 110");

            //crear una excepcion al incrementar en 15 el saldo
            connection.setAutoCommit(false);
            PreparedStatement psUpdateTx= connection.prepareStatement(SQL_UPDATE);
            psUpdateTx.setDouble(1,100+10+15);
            psUpdateTx.setInt(2,1);
            psUpdateTx.execute();
            int x=4/0; //<--para desencadenar el rollback
            logger.warn("Se cargo erroneamente el saldo actual");
            connection.setAutoCommit(true);//--> buena practica dejar en true


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.rollback();
            }catch (SQLException ex){
                ex.getMessage();
            }


        }
    }

    //static para que sea un metodo de la clase, sin static interpreta que es un metodo del objeto y queda fuera del scope
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/C9tx","sa","sa");
    }
}
