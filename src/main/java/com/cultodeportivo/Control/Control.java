package com.cultodeportivo.Control;

import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import java.sql.SQLException;

public class Control {
    private OperacionesAcceso operacionesAcceso;
    private OperacionesEscritura operacionesEscritura;

    public Control() {
        this.operacionesAcceso = new OperacionesAcceso();
        this.operacionesEscritura = new OperacionesEscritura();
    }
    
    public ArrayList<Permiso> obtenerPermisos(){
        return operacionesAcceso.obtenerPermisos();
    }
    
    public ArrayList<Persona> obtenerPersonas(){
        return operacionesAcceso.obtenerPersonas();
    }
    
    public ArrayList<Tipo> obtenerTipos(){
        return operacionesAcceso.obtenerTipos();
    }
    
    public ArrayList<Empleado> obtenerEmpleados(ArrayList<Persona> personas, ArrayList<Tipo> tipos){
        return operacionesAcceso.obtenerEmpleados(personas, tipos);
    }
    
    public ArrayList<Cliente> obtenerClientes(ArrayList<Persona> personas){
        return operacionesAcceso.obtenerClientes(personas);
    }
    
    public ArrayList<Usuario> obtenerUsuarios(ArrayList<Empleado> empleados, ArrayList<Permiso> permisos){
        return operacionesAcceso.obtenerUsuarios(empleados, permisos);
    }
}