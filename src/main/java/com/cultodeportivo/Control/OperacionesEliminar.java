package com.cultodeportivo.Control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class OperacionesEliminar {
    private ConexionOracle conexion;
    private PreparedStatement myStatement;
    
    public OperacionesEliminar(){
        this.conexion = ConexionOracle.getInstance();
    }
    
    public void EliminarFacturaCabecera(int id) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "delete from cd_facturas_cabecera where cab_id = ?";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1,id);

            myStatement.executeUpdate();
            System.out.println("Factura Cabecera eliminado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }
    
    public int eliminarUsuario(int id) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "DELETE FROM cd_usuarios WHERE usr_id = ?";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, id);

            myStatement.executeUpdate();
            System.out.println("Usuario eliminado exitosamente.");
            return 1;

        } catch(java.sql.SQLIntegrityConstraintViolationException e){
            return 2;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta: " + e.getMessage());
            return 0;
        }
    }

    public int eliminarEmpleado(int id) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "DELETE FROM cd_empleados WHERE emp_id = ?";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, id);

            myStatement.executeUpdate();
            System.out.println("Empleado eliminado exitosamente.");
            
            return 1;

        } catch(java.sql.SQLIntegrityConstraintViolationException e){
            return 2;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta: " + e.getMessage());
            return 0;
        }
    }

    public void eliminarPersona(int id) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "DELETE FROM cd_personas WHERE per_id = ?";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, id);

            myStatement.executeUpdate();
            System.out.println("Persona eliminada exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public int eliminarServicio(int id) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "DELETE FROM cd_servicios WHERE ser_id = ?";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, id);

            myStatement.executeUpdate();
            System.out.println("Servicio eliminado exitosamente.");
            return 1;

        } catch(java.sql.SQLIntegrityConstraintViolationException e){
            return 2;
        }catch (SQLException e) {
            return 0;
        }
    }

    public int eliminarCliente(int id) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "DELETE FROM cd_clientes WHERE cli_id = ?";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, id);

            myStatement.executeUpdate();
            System.out.println("Cliente eliminado exitosamente.");
            myStatement.close();
            return 1;

        } catch (SQLIntegrityConstraintViolationException e) {
            return 2;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        } 
        return 0;
    }
    
    
}