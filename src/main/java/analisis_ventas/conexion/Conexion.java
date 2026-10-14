package analisis_ventas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var baseDatos = "analisis_ventas_db";
        var url = "jdbc:mysql://localhost:3306/"+baseDatos;
        var usuario = "root";
        var passwod = "Xss3542..";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion =
                    DriverManager.
                            getConnection
                                    (url, usuario, passwod);
        }catch (Exception e){
            System.out.println("Error al conectarnos ala base de datos");
        }
        return conexion;
    }

    public  static  void main(String[] args){
        var conexion = Conexion.getConexion();
        if (conexion != null)
            System.out.println("Correctamente conectado: "+conexion);
        else
            System.out.println("error al ejecutar la conexion");
    }
}
