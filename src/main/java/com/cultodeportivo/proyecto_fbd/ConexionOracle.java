
package com.cultodeportivo.proyecto_fbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Ajusta esto según tu configuración
    private static final String USER = "DB_SISTEMA";
    private static final String PASSWORD = "trabajo123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        if (conn!= null) {
            System.out.println("Conexión exitosa");
            conn.close(); // Recuerda cerrar la conexión cuando hayas terminado
        } else {
            System.out.println("No se pudo conectar");
        }
    }
}
