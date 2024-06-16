package com.cultodeportivo.Modelos;

public class Usuario {
    private int usrId;
    private String usrNombre;
    private String usrContrasenia;
    private char usrPermiso;
    private int empId;

    // Constructor
    public Usuario(int usrId, String usrNombre, String usrContrasenia, char usrPermiso, int empId) {
        this.usrId = usrId;
        this.usrNombre = usrNombre;
        this.usrContrasenia = usrContrasenia;
        this.usrPermiso = usrPermiso;
        this.empId = empId;
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

    public char getUsrPermiso() {
        return usrPermiso;
    }

    public void setUsrPermiso(char usrPermiso) {
        this.usrPermiso = usrPermiso;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usrId=" + usrId + ", usrNombre=" + usrNombre + ", usrContrasenia=" + usrContrasenia + ", usrPermiso=" + usrPermiso + ", empId=" + empId + '}';
    }

    
    
    

}
