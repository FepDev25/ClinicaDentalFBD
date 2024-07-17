package com.cultodeportivo.Control;

import com.cultodeportivo.Modelos.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OperacionesActualizar {
    
    private ConexionOracle conexion;
    private PreparedStatement myStatement;
    
    public OperacionesActualizar(){
        this.conexion = ConexionOracle.getInstance();
    }
    
    public boolean actualizarPersona(Persona persona)  {
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
    
    public void actualizarEmpleado(int idEmpleado, int caracteristica, Object dato) {
    System.out.println("Iniciando metodo");
    ConexionOracle.getInstance().getConexion();

    String campo = "";
    try {
        switch (caracteristica) {
            case 1:
                campo = "emp_id";
                break;
            case 2:
                campo = "cd_personas_per_id";
                break;
            case 3:
                campo = "cd_tipos_tp_id";
                break;
            default:
                throw new IllegalArgumentException("Característica inválida: " + caracteristica);
        }

        String sql = "UPDATE cd_empleados SET " + campo + " = ? WHERE emp_id = ?";
        System.out.println("SQL: " + sql);
        PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

        if (dato instanceof Integer) {
            myStatement.setInt(1, (Integer) dato);
        } else {
            throw new IllegalArgumentException("Tipo de dato no soportado: " + dato.getClass().getSimpleName());
        }

        myStatement.setInt(2, idEmpleado);
        myStatement.executeUpdate();
        System.out.println("Campo actualizado exitosamente.");

    } catch (SQLException e) {
        System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    public void actualizarUsuario(int idUsuario, int caracteristica, Object dato) {
    System.out.println("Iniciando metodo");
    ConexionOracle.getInstance().getConexion();

    String campo = "";
    try {
        switch (caracteristica) {
            case 1:
                campo = "usr_id";
                break;
            case 2:
                campo = "usr_nombre";
                break;
            case 3:
                campo = "usr_contrasela";
                break;
            case 4:
                campo = "cd_empleados_emp_id";
                break;
            case 5:
                campo = "cd_permisos_prm_id";
                break;
            default:
                throw new IllegalArgumentException("Característica inválida: " + caracteristica);
        }

        String sql = "UPDATE cd_usuarios SET " + campo + " = ? WHERE usr_id = ?";
        System.out.println("SQL: " + sql);
        PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

        if (dato instanceof Integer) {
            myStatement.setInt(1, (Integer) dato);
        } else if (dato instanceof String) {
            myStatement.setString(1, (String) dato);
        } else {
            throw new IllegalArgumentException("Tipo de dato no soportado: " + dato.getClass().getSimpleName());
        }

        myStatement.setInt(2, idUsuario);
        myStatement.executeUpdate();
        System.out.println("Campo actualizado exitosamente.");

    } catch (SQLException e) {
        System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
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

    public void actualizarFacturaDetalle(int idDetalle, int caracteristica, Object dato) {
    System.out.println("Iniciando metodo");
    ConexionOracle.getInstance().getConexion();

    String campo = "";
    try {
        switch (caracteristica) {
            case 1:
                campo = "det_id";
                break;
            case 2:
                campo = "det_producto";
                break;
            case 3:
                campo = "det_cantidad";
                break;
            case 4:
                campo = "det_precio_unitario";
                break;
            case 5:
                campo = "det_subtotal";
                break;
            case 6:
                campo = "det_iva";
                break;
            case 7:
                campo = "det_total";
                break;
            case 8:
                campo = "cd_facturas_cabecera_cab_id";
                break;
            case 9:
                campo = "cd_servicios_ser_id";
                break;
            default:
                throw new IllegalArgumentException("Característica inválida: " + caracteristica);
        }

        String sql = "UPDATE cd_facturas_detalle SET " + campo + " = ? WHERE det_id = ?";
        System.out.println("SQL: " + sql);
        PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

        if (dato instanceof Integer) {
            myStatement.setInt(1, (Integer) dato);
        } else if (dato instanceof String) {
            myStatement.setString(1, (String) dato);
        } else if (dato instanceof Double) {
            myStatement.setDouble(1, (Double) dato);
        } else {
            throw new IllegalArgumentException("Tipo de dato no soportado: " + dato.getClass().getSimpleName());
        }

        myStatement.setInt(2, idDetalle);
        myStatement.executeUpdate();
        System.out.println("Campo actualizado exitosamente.");

    } catch (SQLException e) {
        System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}

    public void actualizarFacturaCabecera(int idCabecera, int caracteristica, Object dato) {
    System.out.println("Iniciando metodo");
    ConexionOracle.getInstance().getConexion();

    String campo = "";
    try {
        switch (caracteristica) {
            case 1:
                campo = "cab_id";
                break;
            case 2:
                campo = "cab_fecha";
                break;
            case 3:
                campo = "cab_subtotal";
                break;
            case 4:
                campo = "cab_iva";
                break;
            case 5:
                campo = "cab_total";
                break;
            case 6:
                campo = "cd_clientes_cli_id";
                break;
            case 7:
                campo = "cd_usuarios_usr_id";
                break;
            default:
                throw new IllegalArgumentException("Característica inválida: " + caracteristica);
        }

        String sql = "UPDATE cd_facturas_cabecera SET " + campo + " = ? WHERE cab_id = ?";
        System.out.println("SQL: " + sql);
        PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

        if (dato instanceof Integer) {
            myStatement.setInt(1, (Integer) dato);
        } else if (dato instanceof String) {
            myStatement.setString(1, (String) dato);
        } else if (dato instanceof Double) {
            myStatement.setDouble(1, (Double) dato);
        } else if (dato instanceof Timestamp) {
            myStatement.setTimestamp(1, (Timestamp) dato);
        } else {
            throw new IllegalArgumentException("Tipo de dato no soportado: " + dato.getClass().getSimpleName());
        }

        myStatement.setInt(2, idCabecera);
        myStatement.executeUpdate();
        System.out.println("Campo actualizado exitosamente.");

    } catch (SQLException e) {
        System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}

    public void actualizarCita(int idCita, int caracteristica, Object dato) {
    System.out.println("Iniciando metodo");
    ConexionOracle.getInstance().getConexion();

    String campo = "";
    try {
        switch (caracteristica) {
            case 1:
                campo = "cit_id";
                break;
            case 2:
                campo = "cit_fecha";
                break;
            case 3:
                campo = "cit_estado";
                break;
            case 4:
                campo = "cd_clientes_cli_id";
                break;
            case 5:
                campo = "cd_empleados_emp_id";
                break;
            default:
                throw new IllegalArgumentException("Característica inválida: " + caracteristica);
        }

        String sql = "UPDATE cd_citas SET " + campo + " = ? WHERE cit_id = ?";
        System.out.println("SQL: " + sql);
        PreparedStatement myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

        if (dato instanceof Integer) {
            myStatement.setInt(1, (Integer) dato);
        } else if (dato instanceof String) {
            myStatement.setString(1, (String) dato);
        } else if (dato instanceof Timestamp) {
            myStatement.setTimestamp(1, (Timestamp) dato);
        } else {
            throw new IllegalArgumentException("Tipo de dato no soportado: " + dato.getClass().getSimpleName());
        }

        myStatement.setInt(2, idCita);
        myStatement.executeUpdate();
        System.out.println("Campo actualizado exitosamente.");

    } catch (SQLException e) {
        System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}

    public void actualizarCliente(int idCliente, int caracteristica, Object dato) {
    System.out.println("Iniciando metodo");
    ConexionOracle.getInstance().getConexion();

    String campo = "";
    try {
        switch (caracteristica) {
            case 1:
                campo = "cli_id";
                break;
            case 2:
                campo = "cd_personas_per_id";
                break;
            case 3:
                campo = "cli_estado";
                break;
            default:
                throw new IllegalArgumentException("Característica inválida: " + caracteristica);
        }

        String sql = "UPDATE cd_clientes SET " + campo + " = ? WHERE cli_id = ?";
        System.out.println("SQL: " + sql);
        myStatement = ConexionOracle.getInstance().getConexion().prepareStatement(sql);

        if (dato instanceof Integer) {
            myStatement.setInt(1, (Integer) dato);
        } else if (dato instanceof String) {
            myStatement.setString(1, (String) dato);
        } else {
            throw new IllegalArgumentException("Tipo de dato no soportado: " + dato.getClass().getSimpleName());
        }

        myStatement.setInt(2, idCliente);
        myStatement.executeUpdate();
        System.out.println("Campo actualizado exitosamente.");

    } catch (SQLException e) {
        System.out.println("Error al establecer la conexión a la base de datos o al ejecutar la consulta.");
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}

    
}
