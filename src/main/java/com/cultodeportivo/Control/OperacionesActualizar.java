package com.cultodeportivo.Control;

import com.cultodeportivo.Modelos.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OperacionesActualizar {

    private ConexionOracle conexion;
    private PreparedStatement myStatement;

    public OperacionesActualizar() {
        this.conexion = ConexionOracle.getInstance();
    }

    public boolean actualizarPersona(Persona persona) {
        System.out.println(persona);
        String sql = "UPDATE cd_personas SET per_cedula = ?, per_nombre = ?, per_apellido = ?, per_direccion = ?, per_telefono = ?, per_correo_electronico = ? WHERE per_id = ?";
        try {
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setString(1, persona.getPerCedula());
            myStatement.setString(2, persona.getPerNombre());
            myStatement.setString(3, persona.getPerApellido());
            myStatement.setString(4, persona.getPerDireccion());
            myStatement.setString(5, persona.getPerTelefono());
            myStatement.setString(6, persona.getPerCorreoElectronico());
            myStatement.setInt(7, persona.getPerId());

            int ejecutar = myStatement.executeUpdate();
            return ejecutar > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar persona. ");
        }
        return false;

    }

    public boolean actualizarEmpleado(Empleado empleado, Tipo tipo) {
        String sql = "UPDATE cd_empleados SET cd_tipos_tip_id = ? WHERE emp_id = ?";
        
        try{
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);
            myStatement.setInt(1, tipo.getTipId());
            myStatement.setInt(1, empleado.getEmpId());
            
            int ejecutar = myStatement.executeUpdate();
            return ejecutar > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar persona. ");
        }
        return false;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE cd_usuarios SET usr_nombre = ?, usr_contrasenia = ? WHERE usr_id = ?";
        
        try{
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);
            
            myStatement.setString(1, usuario.getUsrNombre());
            myStatement.setString(2, usuario.getUsrContrasenia());
            myStatement.setInt(3, usuario.getUsrId());
            
            int ejecutar = myStatement.executeUpdate();
            return ejecutar > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizarServicio(Servicio servicio) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();
        String sql = "UPDATE cd_servicios SET ser_nombre = ?, ser_precio = ?, ser_estado = ?, ser_iva = ? WHERE ser_id = ?";

        try {
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setString(1, servicio.getSerNombre());
            myStatement.setDouble(2, servicio.getSerPrecio());
            myStatement.setString(3, String.valueOf(servicio.getSerEstado()));
            myStatement.setString(4, String.valueOf(servicio.getSerIva()));
            myStatement.setInt(5, servicio.getSerId());

            int ejecutar = myStatement.executeUpdate();
            return ejecutar > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar servicios.");
        }
        return false;

    }

    public void actualizarFacturaDetalle(FacturaDetalle facturaDet) {
    }

    public void actualizarFacturaCabecera(FacturaCabecera facturaCab) {
    }

    public void actualizarCita(Cita cita) {
    }

    public void actualizarCliente(Cliente cliente) {
    }

}
