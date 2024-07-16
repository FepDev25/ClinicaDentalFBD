package com.cultodeportivo.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionOracle {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "DB_SISTEMA";
    private static final String PASSWORD = "trabajo123";

    private static ConexionOracle instancia = null;
    private Connection conexion;

    // Constructor privado para asegurar que esta clase no pueda ser instanciada
    private ConexionOracle() {
        try {
            // Carga el driver de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Reemplaza los valores de usuario y contraseña con tus credenciales reales
            this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion Creada.");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConexionOracle getInstance() {
        if (instancia == null ) {
            System.out.println("Creando conexion...");
            instancia = new ConexionOracle();
        }
        return instancia;
    }

    public Connection getConexion() {
        try {
            if (this.conexion.isClosed()){
                instancia = new ConexionOracle();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conexion;
    }
}
