package com.cultodeportivo.Modelos;

public class Persona {
    private int perId;
    private String perCedula;
    private String perNombre;
    private String perApellido;
    private String perDireccion;
    private String perTelefono;
    private String perCorreoElectronico;

    // Constructor
    public Persona(int perId, String perCedula, String perNombre, String perApellido, String perDireccion, String perTelefono, String perCorreoElectronico) {
        this.perId = perId;
        this.perCedula = perCedula;
        this.perNombre = perNombre;
        this.perApellido = perApellido;
        this.perDireccion = perDireccion;
        this.perTelefono = perTelefono;
        this.perCorreoElectronico = perCorreoElectronico;
    }

    // Getters
    public int getPerId() {
        return perId;
    }

    public String getPerCedula() {
        return perCedula;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public String getPerDireccion() {
        return perDireccion;
    }

    public String getPerTelefono() {
        return perTelefono;
    }

    public String getPerCorreoElectronico() {
        return perCorreoElectronico;
    }

    // Setters
    public void setPerId(int perId) {
        this.perId = perId;
    }

    public void setPerCedula(String perCedula) {
        this.perCedula = perCedula;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public void setPerDireccion(String perDireccion) {
        this.perDireccion = perDireccion;
    }

    public void setPerTelefono(String perTelefono) {
        this.perTelefono = perTelefono;
    }

    public void setPerCorreoElectronico(String perCorreoElectronico) {
        this.perCorreoElectronico = perCorreoElectronico;
    }

    // Método toString
    @Override
    public String toString() {
        return "Persona{" +
                "perId=" + perId +
                ", perCedula='" + perCedula + '\'' +
                ", perNombre='" + perNombre + '\'' +
                ", perApellido='" + perApellido + '\'' +
                ", perDireccion='" + perDireccion + '\'' +
                ", perTelefono='" + perTelefono + '\'' +
                ", perCorreoElectronico='" + perCorreoElectronico + '\'' +
                '}';
    }
}

