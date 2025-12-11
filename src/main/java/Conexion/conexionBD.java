package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class conexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/buffet";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para realizar una consulta SQL
    public static ResultSet ejecutarConsulta(String sql, Object... parametros) throws SQLException {
        Connection conexion = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conexion = getConnection();
            pst = conexion.prepareStatement(sql);
            
            // Establecer los parámetros de la consulta
            for (int i = 0; i < parametros.length; i++) {
                pst.setObject(i + 1, parametros[i]);
            }
            
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propagar la excepción
        } finally {
            // Los recursos serán cerrados por el usuario del método
        }
    }

    // Método para cerrar los recursos
    public static void cerrarRecursos(ResultSet rs, PreparedStatement pst, Connection conexion) {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
   try {
            // Intentar obtener la conexión
            Connection conexion = conexionBD.getConnection();
            
            if (conexion != null) {
                System.out.println("Conexión a la base de datos establecida correctamente.");
                conexion.close(); // Cierra la conexión después de verificar
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}