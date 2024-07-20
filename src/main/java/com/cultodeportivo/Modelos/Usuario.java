package com.cultodeportivo.Modelos;

import java.util.Objects;

public class Usuario {
    private int usrId;
    private String usrNombre;
    private String usrContrasenia;
    private Empleado empleado; 
    private Permiso permiso;

    // Constructor
    public Usuario(int usrId, String usrNombre, String usrContrasenia, Empleado empleado, Permiso permiso) {
        this.usrId = usrId;
        this.usrNombre = usrNombre;
        this.usrContrasenia = usrContrasenia;
        this.empleado = Objects.requireNonNull(empleado, "El objeto 'empleado' no puede ser nulo");
        this.permiso = Objects.requireNonNull(permiso, "El objeto 'permiso' no puede ser nulo");
    }
    
    public Usuario(String usrNombre, String usrContrasenia, Empleado empleado, Permiso permiso) {
        this.usrNombre = usrNombre;
        this.usrContrasenia = usrContrasenia;
        this.empleado = Objects.requireNonNull(empleado, "El objeto 'empleado' no puede ser nulo");
        this.permiso = Objects.requireNonNull(permiso, "El objeto 'permiso' no puede ser nulo");
    }

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public String getUsrNombre() {
        return usrNombre;
    }

    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    public String getUsrContrasenia() {
        return usrContrasenia;
    }

    public void setUsrContrasenia(String usrContrasenia) {
        this.usrContrasenia = usrContrasenia;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        if (this.empleado!= null) {
            this.empleado.setEmpId(usrId);
        }
        this.empleado = Objects.requireNonNull(empleado, "El objeto 'empleado' no puede ser nulo");
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        if (this.permiso!= null) {
            this.permiso.setPrmId(usrId); 
        }
        this.permiso = Objects.requireNonNull(permiso, "El objeto 'permiso' no puede ser nulo");
    }

    // Método toString
    @Override
    public String toString() {
        return "Usuario{" +
                "usrId=" + usrId +
                ", usrNombre='" + usrNombre + '\'' +
                ", usrContrasenia='" + usrContrasenia + '\'' +
                ", empleado=" + empleado +
                ", permiso=" + permiso +
                '}';
    }
}
