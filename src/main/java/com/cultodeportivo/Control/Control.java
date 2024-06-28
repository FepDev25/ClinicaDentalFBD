package com.cultodeportivo.Control;

import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import com.cultodeportivo.proyecto_fbd.*;
import java.sql.SQLException;

public class Control {
    private OperacionesAcceso operacionesAcceso;
    private OperacionesEscritura operacionesEscritura;
    public PrimaryController controller;
    public SecondaryController controller2;

    public Control(PrimaryController controller) {
        this.operacionesAcceso = new OperacionesAcceso();
        this.operacionesEscritura = new OperacionesEscritura();
        this.controller = controller;
    }
    
    public Control(SecondaryController controller2) {
        this.operacionesAcceso = new OperacionesAcceso();
        this.operacionesEscritura = new OperacionesEscritura();
        this.controller2 = controller2;
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
    
    public boolean cargarListasController1(){
        controller.setPermisos(this.obtenerPermisos());
        controller.setPersonas(this.obtenerPersonas());
        controller.setTipos(this.obtenerTipos());
        
        if ((!controller.getPersonas().isEmpty()) && (!controller.getTipos().isEmpty())){
            controller.setEmpleados(this.obtenerEmpleados(controller.getPersonas(), controller.getTipos()));
            controller.setClientes(this.obtenerClientes(controller.getPersonas()));
            if ((!controller.getEmpleados().isEmpty()) && (!controller.getPermisos().isEmpty())){
                controller.setUsuarios(this.obtenerUsuarios(controller.getEmpleados(), controller.getPermisos())); 
            } else{
                return false;
            }
        } else{
            return false;
        }
        return true;
    }
}