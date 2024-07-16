package com.cultodeportivo.Control;

import com.cultodeportivo.Modelos.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;

public class OperacionesEscritura {

    private ConexionOracle conexion;
    private PreparedStatement myStatement;

    public OperacionesEscritura() {
        this.conexion = ConexionOracle.getInstance();
    }

    public int CrearPersona(Persona persona) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String cedula = persona.getPerCedula();
            String nombre = persona.getPerNombre();
            String apellido = persona.getPerApellido();
            String direccion = persona.getPerDireccion();
            String telefono = persona.getPerTelefono();
            String correo = persona.getPerCorreoElectronico();

            String sql = "INSERT INTO CD_PERSONAS VALUES (personas_seq.nextval, ?, ?, ?, ?, ?, ?)";
            System.out.println("SQL: " + sql);
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

    public void CrearEmpleado(Empleado empleado) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            Persona persona = empleado.getPersona();
            Tipo tipo = empleado.getTipo();
            int perId = persona != null ? persona.getPerId() : 0;
            int tipId = tipo != null ? tipo.getTipId() : 0;

            String sql = "INSERT INTO CD_EMPLEADOS VALUES (empleados_seq.nextval, ?, ?)";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, perId);
            myStatement.setInt(2, tipId);

            myStatement.executeUpdate();
            System.out.println("Empleado creado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public void CrearUsuario(Usuario user) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_USUARIOS VALUES (usuarios_seq.nextval, ?, ?, ?, ?)";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setString(1, user.getUsrNombre());
            myStatement.setString(2, user.getUsrContrasenia());
            myStatement.setInt(3, user.getEmpleado().getEmpId());
            myStatement.setInt(4, user.getPermiso().getPrmId());

            myStatement.executeUpdate();
            System.out.println("Usuario creado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public void CrearServicio(Servicio servicio) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_SERVICIOS (ser_id, ser_nombre, ser_precio, ser_iva, ser_estado) VALUES (servicios_seq.nextval, ?, ?, ?, ?)";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setString(1, servicio.getSerNombre());
            myStatement.setDouble(2, servicio.getSerPrecio());
            myStatement.setString(3, String.valueOf(servicio.getSerEstado()));
            myStatement.setString(3, String.valueOf(servicio.getSerIva()));

            myStatement.executeUpdate();
            System.out.println("Servicio creado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public void CrearFacturaCabecera(Timestamp cabFecha, double cabSubtotal, double cabIva, double cabTotal, int cliId, int usrId) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_FACTURAS_CABECERA VALUES (facturas_cabecera_seq.nextval, ?, ?, ?, ?, ?, ?)";
            System.out.println("SQL: " + sql);
            PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setTimestamp(1, cabFecha);
            myStatement.setDouble(2, cabSubtotal);
            myStatement.setDouble(3, cabIva);
            myStatement.setDouble(4, cabTotal);
            myStatement.setInt(5, cliId);
            myStatement.setInt(6, usrId);

            myStatement.executeUpdate();
            System.out.println("Factura cabecera creada exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public void CrearFacturaDetalle(int detProducto, double detCantidad, double detPrecio, int cabId, int serId) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_FACTURAS_DETALLE VALUES (facturas_detalle_seq.nextval, ?, ?, ?, ?, ?)";
            System.out.println("SQL: " + sql);
            PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, detProducto);
            myStatement.setDouble(2, detCantidad);
            myStatement.setDouble(3, detPrecio);
            myStatement.setInt(4, cabId);
            myStatement.setInt(5, serId);

            myStatement.executeUpdate();
            System.out.println("Factura detalle creada exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public void CrearCita(Timestamp citFecha, char citEstado, int cliId, int empId) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_CITAS VALUES (citas_seq.nextval, ?, ?, ?, ?)";
            System.out.println("SQL: " + sql);
            PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setTimestamp(1, citFecha);
            myStatement.setString(2, String.valueOf(citEstado));
            myStatement.setInt(3, cliId);
            myStatement.setInt(4, empId);

            myStatement.executeUpdate();
            System.out.println("Cita creada exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public boolean CrearCliente(Cliente cliente) {
        System.out.println("Iniciando metodo");
        ConexionOracle.getInstance().getConexion();

        try {
            String sql = "INSERT INTO CD_CLIENTES VALUES (clientes_seq.nextval, ?, ?)";
            System.out.println("SQL: " + sql);
            myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

            myStatement.setInt(1, cliente.getPersona().getPerId());
            myStatement.setString(2, String.valueOf(cliente.getCliEstado()));

            int ejecutar = myStatement.executeUpdate();
            System.out.println("Cliente creado exitosamente.");
            myStatement.close();
            return ejecutar > 0;

        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
            e.printStackTrace();
        }
        return false;
    }

}
