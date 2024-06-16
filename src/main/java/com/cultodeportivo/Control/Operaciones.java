package com.cultodeportivo.Control;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Operaciones {

    private ConexionOracle conexion;
    private PreparedStatement myStatement;

    public Operaciones() {
        this.conexion = ConexionOracle.getInstance();
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_USUARIOS";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

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
            myStatement.close();
            return usuarios;
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
