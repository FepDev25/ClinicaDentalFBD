package com.cultodeportivo.Control;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OperacionesAcceso {
    private ConexionOracle conexion;
    private PreparedStatement myStatement;
    public Emparejador empj;

    public OperacionesAcceso() {
        this.conexion = ConexionOracle.getInstance();
        empj = new Emparejador();
    }

    public ArrayList<Permiso> obtenerPermisos(){
        ArrayList<Permiso> permisos = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_PERMISOS";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int prmId = rs.getInt("prm_id");
                String prmTipo = rs.getString("prm_tipo");

                Permiso permiso = new Permiso(prmId, prmTipo);
                permisos.add(permiso);
            }
            myStatement.close();
            return permisos;
        } catch (SQLException e) {
            System.err.println("Error al obtener permisos: " + e.getMessage());        
        }
        return null;
    }
    
    public Permiso obtenerPermisoPorNombre(String nombre){
        Permiso permiso = null;
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_PERMISOS WHERE prm_tipo = ?;";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);
            myStatement.setString(1, nombre);
            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int prmId = rs.getInt("prm_id");
                String prmTipo = rs.getString("prm_tipo");

                permiso = new Permiso(prmId, prmTipo);
            }
            myStatement.close();
            return permiso;
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
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int tipId = rs.getInt("tip_id");
                String tipNombre = rs.getString("tip_nombre");

                Tipo tipo = new Tipo(tipId, tipNombre);
                tipos.add(tipo);
            }
            myStatement.close();
            return tipos;
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos: " + e.getMessage());        
        }
        return null;
    }
    
    public Tipo obtenerTipsPorNombre(String nombre){
        Tipo tipo = null;
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_TIPOS WHERE tip_nombre = ?";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);
            myStatement.setString(1, nombre);
            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int tipId = rs.getInt("tip_id");
                String tipNombre = rs.getString("tip_nombre");

                tipo = new Tipo(tipId, tipNombre);
            }
            myStatement.close();
            return tipo;
        } catch (SQLException e) {
            System.err.println("Error al obtener tipo: " + e.getMessage());        
        }
        return null;
    }
    
    public ArrayList<Empleado> obtenerEmpleados(ArrayList<Persona> personas, ArrayList<Tipo> tipos){
        ArrayList<Empleado> empleados = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_EMPLEADOS";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                int per_id = rs.getInt("cd_personas_per_id");
                int tip_id = rs.getInt("cd_tipos_tip_id");

                Empleado empleado = empj.emparejarEmpleado(empId, per_id, tip_id, personas, tipos);
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
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int cliId = rs.getInt("cli_id");
                char cliEstado = rs.getString("cli_estado").charAt(0);
                int perId = rs.getInt("cd_personas_per_id");

                Cliente cliente = empj.emparejarCliente(cliId, cliEstado, perId, personas);
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
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_USUARIOS";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int usrId = rs.getInt("usr_id");
                String usrNombre = rs.getString("usr_nombre");
                String usrContrasenia = rs.getString("usr_contrasenia");
                int empId = rs.getInt("cd_empleados_emp_id");
                int prmId = rs.getInt("cd_permisos_prm_id");

                Usuario usuario = empj.emparejarUsuarios(usrId, usrNombre, usrContrasenia, empId, prmId, empleados, permisos);
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
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_SERVICIOS";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int serId = rs.getInt("ser_id");
                String serNombre = rs.getString("ser_nombre");
                Float serPrecio = rs.getFloat("ser_precio");
                char tiene_iva = rs.getString("ser_iva").charAt(0);
                char estado = rs.getString("ser_estado").charAt(0);
                
                Servicio servicio = new Servicio(serId, serNombre, serPrecio, tiene_iva, estado);
                servicios.add(servicio);
            }
            myStatement.close();
            return servicios;
        } catch (SQLException e) {
            System.err.println("Error al obtener servicios: " + e.getMessage());
        }
        return null;
    }
    
    
        
    public ArrayList<Cita> obtenerCitas(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados) {
        ArrayList<Cita> citas = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_CITAS";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int citId = rs.getInt("cit_id");
                Timestamp fecha = rs.getTimestamp("cit_fecha");
                LocalDateTime citFecha = fecha.toLocalDateTime();
                
                char citEstado = rs.getString("cit_estado").charAt(0);
                int citCliente = rs.getInt("cd_clientes_cli_id");
                int citEmpleado = rs.getInt("cd_empleados_emp_id");
                
                Cita cita = empj.emparejarCitas(citId, citFecha, citCliente, citEmpleado, citEstado, empleados, clientes);
                citas.add(cita);
            }
            myStatement.close();
            return citas;
        } catch (SQLException e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<FacturaCabecera> obtenerFacturasCabecera(ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios) {
        ArrayList<FacturaCabecera> cabeceras = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_FACTURAS_CABECERA";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int  cabId = rs.getInt("cab_id");
                Timestamp fecha = rs.getTimestamp("cab_fecha");
                LocalDateTime cabFecha = fecha.toLocalDateTime();
                double  cabSubtotal = rs.getDouble("cab_subtotal");
                double  cabTotalIva = rs.getDouble("cab_total_iva");
                double cabTotal = rs.getDouble("cab_total");
                int  cabCliente = rs.getInt("cd_clientes_cli_id");
                int  cabUsuario = rs.getInt("cd_usuarios_usr_id");
                
                FacturaCabecera f = empj.emparejarFacturaCabecera(cabCliente, cabUsuario, clientes, usuarios);
                f.setCabId(cabId);
                f.setCabFecha(cabFecha);
                f.setCabTotalIva(cabTotalIva);
                f.setCabSubtotal(cabSubtotal);
                f.setCabTotal(cabTotal);
                
                cabeceras.add(f);
            }
            myStatement.close();
            return cabeceras;
        } catch (SQLException e) {
            System.err.println("Error al obtener Cabeceras de facturas: " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<FacturaDetalle> obtenerFacturasDetalle(ArrayList<Servicio> servicios, ArrayList<FacturaCabecera> cabeceras) {
        ArrayList<FacturaDetalle> detalles = new ArrayList<>();
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_FACTURAS_DETALLE";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int  detId = rs.getInt("det_id");
                double  detPrecioUnitario = rs.getDouble("det_Precio_Unitario");
                double  detSubtotal = rs.getDouble("det_subtotal");
                double  detIva = rs.getDouble("det_iva");
                double  detTotal = rs.getDouble("det_total");
                int detCantidad = rs.getInt("det_cantidad");
                
                int  detServicio = rs.getInt("cd_servicios_ser_id");
                int  detCabecera = rs.getInt("cd_facturas_cabecera_cab_id");
  
                
                FacturaDetalle f = empj.emparejarFacturaDetalle(detCabecera, detServicio, cabeceras, servicios);
                f.setDetId(detId);
                f.setDetPrecioUnitario(detPrecioUnitario);
                f.setDetSubtotal(detSubtotal);
                f.setDetIva(detIva);
                f.setDetCantidad(detCantidad);
                f.setDetTotal(detTotal);
                
                detalles.add(f);
            }
            myStatement.close();
            return detalles;
        } catch (SQLException e) {
            System.err.println("Error al obtener Detalles de facturas: " + e.getMessage());
        }
        return null;
    }
    
    
    public Impuesto obtenerImpuesto() {
        ConexionOracle.getInstance().getConexion();
        
        try {
            String sql = "SELECT * FROM CD_IMPUESTOS WHERE imp_nombre = '15'";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            ResultSet rs = myStatement.executeQuery(); 

            while (rs.next()) {
                int  imp_id = rs.getInt("imp_id");
                int imp_valor = rs.getInt("imp_valor");
                String imp_nombre = rs.getString("imp_nombre");
                
                Impuesto i = new Impuesto(imp_id,imp_valor,imp_nombre.charAt(0));
                return i;
                        
            }
            myStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener Detalles de facturas: " + e.getMessage());
        }
        return null;
    }
    
}
