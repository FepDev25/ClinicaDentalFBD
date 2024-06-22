package com.cultodeportivo.Modelos;

public class Tipo {
    private int tipId;
    private String tipNombre;

    // Constructor
    public Tipo(int tipId, String tipNombre) {
        this.tipId = tipId;
        this.tipNombre = tipNombre;
    }

    // Getters
    public int getTipId() {
        return tipId;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    // Setters
    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
    }

    // Método toString
    @Override
    public String toString() {
        return "Tipo{" +
                "tipId=" + tipId +
                ", tipNombre='" + tipNombre + '\'' +
                '}';
    }
}
