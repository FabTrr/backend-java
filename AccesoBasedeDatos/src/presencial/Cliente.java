package presencial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cliente {
    public static void main(String[] args) {
        //crear conexion
        Connection connection = null;
        try{
            connection = getConnection();
            Statement statement = connection.createStatement();//lleva los objetos java a las tablas sql
            //cuidado con los espacios
            statement.execute("DROP TABLE IF EXISTS ANIMALES; CREATE TABLE ANIMALES" +
                    "(ID INT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, TIPO VARCHAR(100) NOT NULL)");
            //insert 3 animales
            statement.execute("INSERT INTO ANIMALES VALUES(1,'PEDRO','PERRO'),(2,'CHISPAS','CONEJO'),(3,'TITI','GATO')");
            //select todos los animales
            ResultSet rs= statement.executeQuery("SELECT * FROM ANIMALES");
            //recorrer para traer los datos en un ciclo while
            while(rs.next()){
                System.out.println("Nombre: "+rs.getString(2)+" Tipo: "+rs.getString(3));
            }
            System.out.println("***************************************************************");
            //eliminar un registro
            statement.execute("DELETE FROM ANIMALES WHERE ID=1");//select todos los animales
            ResultSet rs1= statement.executeQuery("SELECT * FROM ANIMALES");
            //recorrer con while para traer los datos
            while(rs1.next()){
                System.out.println("Nombre: "+rs1.getString(2)+" Tipo: "+rs1.getString(3));
            }
        }catch (Exception e){
            e.printStackTrace();

        }

    }
    //la conexion a la base de datos es un metodo de la clase (no del objeto)
    public static Connection getConnection() throws Exception{
        //cargo el driver jdbc
        Class.forName("org.h2.Driver");
        //url, user y pass...url: direccion y nombre de la base de datos
        return DriverManager.getConnection("jdbc:h2:~/c8Animales","sa","sa");
    }

}
