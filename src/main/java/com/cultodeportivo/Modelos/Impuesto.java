package com.cultodeportivo.Modelos;

public class Impuesto {
    private int impId;
    private double impValor;
    private char impNombre;

    // Constructor
    public Impuesto(int impId, double impValor, char impNombre) {
        this.impId = impId;
        this.impValor = impValor;
        this.impNombre = impNombre;
    }

    public int getImpId() {
        return impId;
    }

    public void setImpId(int impId) {
        this.impId = impId;
    }

    public double getImpValor() {
        return impValor;
    }

    public void setImpValor(double impValor) {
        this.impValor = impValor;
    }

    public char getImpNombre() {
        return impNombre;
    }

    public void setImpNombre(char impNombre) {
        this.impNombre = impNombre;
    }
    
    

    @Override
    public String toString() {
        return "Impuesto{" +
                "impId=" + impId +
                ", impValor=" + impValor +
                '}';
    }
}
