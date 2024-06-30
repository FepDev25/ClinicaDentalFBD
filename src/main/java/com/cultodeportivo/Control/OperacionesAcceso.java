package com.cultodeportivo.Control;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperacionesAcceso {
    private ConexionOracle conexion;
    private PreparedStatement myStatement;
    private Emparejador empj;

    public OperacionesAcceso() {
        this.conexion = ConexionOracle.getInstance();
        empj = new Emparejador();
    }

    public ArrayList<Permiso> obtenerPermisos(){
        ArrayList<Permiso> permisos = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_PERMISOS";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            System.out.println(rs);
            while (rs.next()) {
                int prmId = rs.getInt("prm_id");
                String prmTipo = rs.getString("prm_tipo");

                Permiso permiso = new Permiso(prmId, prmTipo);
                System.out.println(permiso);
                permisos.add(permiso);
            }
            myStatement.close();
            return permisos;
        } catch (SQLException e) {
            System.err.println("Error al obtener permisos: " + e.getMessage());        
        }
        return null;
    }
    
    public ArrayList<Persona> obtenerPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_PERSONAS";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery();

            while (rs.next()) {
                int perId = rs.getInt("per_id"); 
                String perCedula = rs.getString("per_cedula"); 
                String perNombre = rs.getString("per_nombre"); 
                String perApellido = rs.getString("per_apellido"); 
                String perDireccion = rs.getString("per_direccion"); 
                String perTelefono = rs.getString("per_telefono"); 
                String perCorreo = rs.getString("per_correo_electronico"); 

                Persona persona = new Persona(perId, perCedula,perNombre,perApellido,perDireccion,perTelefono,perCorreo);
                System.out.println(persona);
                personas.add(persona);
            }
            myStatement.close();
            return personas;
        } catch (SQLException e) {
            System.err.println("Error al obtener personas: " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Tipo> obtenerTipos(){
        ArrayList<Tipo> tipos = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_TIPOS";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            System.out.println(rs);
            while (rs.next()) {
                int tipId = rs.getInt("tip_id");
                String tipNombre = rs.getString("tip_nombre");

                Tipo tipo = new Tipo(tipId, tipNombre);
                System.out.println(tipo);
                tipos.add(tipo);
            }
            myStatement.close();
            return tipos;
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos: " + e.getMessage());        
        }
        return null;
    }
    
    public ArrayList<Empleado> obtenerEmpleados(ArrayList<Persona> personas, ArrayList<Tipo> tipos){
        ArrayList<Empleado> empleados = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_EMPLEADOS";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            System.out.println(rs);
            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                int per_id = rs.getInt("per_id");
                int tip_id = rs.getInt("tip_id");

                Empleado empleado = empj.emparejarEmpleado(empId, per_id, tip_id, personas, tipos);
                System.out.println(empleado);
                empleados.add(empleado);
            }
            myStatement.close();
            
            return empleados;
        } catch (SQLException e) {
            System.err.println("Error al obtener empleados: " + e.getMessage());        
        }
        return null;
    }
    
    public ArrayList<Cliente> obtenerClientes(ArrayList<Persona> personas){
        ArrayList<Cliente> clientes = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_CLIENTES";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            System.out.println(rs);
            while (rs.next()) {
                int cliId = rs.getInt("cli_id");
                char cliEstado = rs.getString("cli_estado").charAt(0);
                int perId = rs.getInt("per_id");

                Cliente cliente = empj.emparejarCliente(cliId, cliEstado, perId, personas);
                System.out.println(cliente);
                clientes.add(cliente);
            }
            myStatement.close();
            
            return clientes;
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());        
        }
        return null;
    }

    public ArrayList<Usuario> obtenerUsuarios(ArrayList<Empleado> empleados, ArrayList<Permiso> permisos) {
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
                int empId = rs.getInt("emp_id");
                int prmId = rs.getInt("prm_id");

                Usuario usuario = empj.emparejarUsuarios(usrId, usrNombre, usrContrasenia, empId, prmId, empleados, permisos);
                System.out.println(usuario);
                usuarios.add(usuario);
            }
            myStatement.close();
            return usuarios;
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Servicio> obtenerServicios() {
        ArrayList<Servicio> servicios = new ArrayList<>();
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_SERVICIOS";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            System.out.println(rs);
            while (rs.next()) {
                int serId = rs.getInt("ser_id");
                String serNombre = rs.getString("ser_nombre");
                Float serPrecio = rs.getFloat("ser_precio");
                char tiene_iva = rs.getString("ser_iva").charAt(0);
                char estado = rs.getString("ser_estado").charAt(0);
                
                Servicio servicio = new Servicio(serId, serNombre, serPrecio, tiene_iva, estado);
                System.out.println(servicio);
                servicios.add(servicio);
            }
            myStatement.close();
            return servicios;
        } catch (SQLException e) {
            System.err.println("Error al obtener servicios: " + e.getMessage());
        }
        return null;
    }

}
