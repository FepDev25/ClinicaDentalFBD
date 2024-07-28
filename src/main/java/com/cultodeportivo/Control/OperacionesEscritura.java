package com.cultodeportivo.Control;

import com.cultodeportivo.Modelos.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.sql.Types;

public class OperacionesEscritura {

    private ConexionOracle conexion;
    private PreparedStatement myStatement;

    public OperacionesEscritura() {
        this.conexion = ConexionOracle.getInstance();
    }

    public int CrearPersona(Persona persona) {
        ConexionOracle.getInstance().getConexion();

        try {
            String cedula = persona.getPerCedula();
            String nombre = persona.getPerNombre();
            String apellido = persona.getPerApellido();
            String direccion = persona.getPerDireccion();
            String telefono = persona.getPerTelefono();
            String correo = persona.getPerCorreoElectronico();

            String sql = "INSERT INTO CD_PERSONAS VALUES (personas_seq.nextval, ?, ?, ?, ?, ?, ?)";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setString(1, cedula);
            myStatement.setString(2, nombre);
            myStatement.setString(3, apellido);
            myStatement.setString(4, direccion);
            myStatement.setString(5, telefono);
            myStatement.setString(6, correo);

            myStatement.executeUpdate();
            System.out.println("Persona creada exitosamente.");
            myStatement.close();
            return 1;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Exception de Constraint.");
            return 2;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        }
        return 0;
    }

    public boolean CrearEmpleado(Empleado empleado) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {

            String sql = "INSERT INTO CD_EMPLEADOS VALUES (empleados_seq.nextval, ?, ?)";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, empleado.getPersona().getPerId());
            myStatement.setInt(2, empleado.getTipo().getTipId());

            int filas = myStatement.executeUpdate();
            myStatement.close();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta: " + e.getMessage());
        }
        return false;
    }

    public boolean CrearUsuario(Usuario user) {
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_USUARIOS VALUES (usuarios_seq.nextval, ?, ?, ?, ?)";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setString(1, user.getUsrNombre());
            myStatement.setString(2, user.getUsrContrasenia());
            myStatement.setInt(3, user.getEmpleado().getEmpId());
            myStatement.setInt(4, user.getPermiso().getPrmId());

            int filas = myStatement.executeUpdate();
            myStatement.close();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta: " + e.getMessage());
        }
        return false;
    }

    public boolean CrearServicio(Servicio servicio) {
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_SERVICIOS (ser_id, ser_nombre, ser_precio, ser_iva, ser_estado) VALUES (servicios_seq.nextval, ?, ?, ?, ?)";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setString(1, servicio.getSerNombre());
            myStatement.setDouble(2, servicio.getSerPrecio());
            myStatement.setString(3, String.valueOf(servicio.getSerIva()));
            myStatement.setString(4, String.valueOf(servicio.getSerEstado()));

            int ejecutar = myStatement.executeUpdate();
            myStatement.close();
            return ejecutar > 0;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
        return false;
    }

    public int CrearFacturaCabecera(FacturaCabecera cabecera) {
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "{CALL insertFacturaCabecera(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = ConexionOracle.getInstance().getConexion().prepareCall(sql);

            Timestamp fechaT = Timestamp.valueOf(cabecera.getCabFecha());

            // Registrar parámetros de entrada
            callableStatement.setTimestamp(1, fechaT);
            callableStatement.setDouble(2, cabecera.getCabSubtotal());
            callableStatement.setDouble(3, cabecera.getCabTotalIva());
            callableStatement.setDouble(4, cabecera.getCabTotal());
            callableStatement.setInt(5, cabecera.getCliente().getCliId());
            callableStatement.setInt(6, cabecera.getUsuario().getUsrId());

            // Registrar parámetro de salida
            callableStatement.registerOutParameter(7, java.sql.Types.INTEGER);

            // Ejecutar la consulta
            callableStatement.execute();

            int generatedId = callableStatement.getInt(7);
            callableStatement.close();

            return generatedId;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta: " + e.getMessage());
            return -1;
        }
    }

    public boolean CrearFacturaDetalle(FacturaDetalle detalle) {
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_FACTURAS_DETALLE VALUES (facturas_detalle_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setDouble(1, detalle.getDetSubtotal());
            myStatement.setDouble(2, detalle.getDetSubtotal());
            myStatement.setDouble(3, detalle.getDetIva());
            myStatement.setInt(4, detalle.getDetCantidad());
            myStatement.setInt(5, detalle.getServicio().getSerId());
            myStatement.setInt(6, detalle.getFacturaCabecera().getCabId());
            myStatement.setDouble(7, detalle.getDetTotal());

            myStatement.executeUpdate();
            myStatement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta: " + e.getMessage());
        }
        return false;
    }

    public boolean CrearCita(Cita cita) {
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_CITAS VALUES (citas_seq.nextval, ?, ?, ?, ?)";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setTimestamp(1, Timestamp.valueOf(cita.getCitFecha()));
            myStatement.setString(2, String.valueOf(cita.getCitEstado()));
            myStatement.setInt(3, cita.getCliente().getCliId());
            myStatement.setInt(4, cita.getEmpleado().getEmpId());

            int filas = myStatement.executeUpdate();
            myStatement.close();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta: " + e.getMessage());
            return false;
        }
    }

    public boolean CrearCliente(Cliente cliente) {
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_CLIENTES VALUES (clientes_seq.nextval, ?, ?)";
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, cliente.getPersona().getPerId());
            myStatement.setString(2, String.valueOf(cliente.getCliEstado()));

            int ejecutar = myStatement.executeUpdate();
            myStatement.close();
            return ejecutar > 0;

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
        return false;
    }

}
