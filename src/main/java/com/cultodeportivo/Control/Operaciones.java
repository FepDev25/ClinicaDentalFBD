package com.cultodeportivo.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;

public class Operaciones {
    private ConexionOracle conexion;

    public Operaciones() {
        this.conexion = ConexionOracle.getInstance();
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = this.conexion.getConexion()) {

            String sql = "SELECT * FROM CD_USUARIOS";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

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
            System.err.println(e);
        }
        return usuarios;
    }
}
