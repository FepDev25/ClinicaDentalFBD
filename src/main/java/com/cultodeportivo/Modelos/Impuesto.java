package com.cultodeportivo.Modelos;

public class Impuesto {
    private int impId;
    private double impValor;

    // Constructor
    public Impuesto(int impId, double impValor) {
        this.impId = impId;
        this.impValor = impValor;
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

    @Override
    public String toString() {
        return "Impuesto{" +
                "impId=" + impId +
                ", impValor=" + impValor +
                '}';
    }
}
