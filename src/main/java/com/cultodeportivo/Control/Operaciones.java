package com.cultodeportivo.Control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import java.sql.Statement;

public class Operaciones {
    private ConexionOracle conexion;

    public Operaciones() {
        this.conexion = ConexionOracle.getInstance();
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        System.out.println("Iniciando metodo");
        
        try (Connection conn = this.conexion.getConexion()) {
            
            String sql = "SELECT * FROM CD_USUARIOS";
            System.out.println(sql);
            Statement myStatement = conn.createStatement();

            ResultSet rs = myStatement.executeQuery(sql);
            System.out.println("Obtenido");
            System.out.println(rs);
            while (rs.next()) {
                int usrId = rs.getInt("usr_id");
                String usrNombre = rs.getString("usr_nombre");
                String usrContrasenia = rs.getString("usr_contrasenia");
                char usrPermiso = rs.getString("usr_permiso").charAt(0); 
                int empId = rs.getInt("emp_id");

                Usuario usuario = new Usuario(usrId, usrNombre, usrContrasenia, usrPermiso, empId);
                System.out.println(usuario);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza completa para facilitar la depuración
        }
        return null;
    }
}