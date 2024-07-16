package com.cultodeportivo.Modelos;

public class Servicio {
    private int serId;
    private String serNombre;
    private double serPrecio;
    private char serIva;
    private char serEstado;

    public Servicio(int serId, String serNombre, double serPrecio, char serIva, char serEstado) {
        this.serId = serId;
        this.serNombre = serNombre;
        this.serPrecio = serPrecio;
        this.serIva = serIva;
        this.serEstado = serEstado;
    }
    
    public Servicio(String serNombre, double serPrecio, char serIva, char serEstado) {
        this.serNombre = serNombre;
        this.serPrecio = serPrecio;
        this.serIva = serIva;
        this.serEstado = serEstado;
    }

    public int getSerId() {
        return serId;
    }

    public void setSerId(int serId) {
        this.serId = serId;
    }

    public String getSerNombre() {
        return serNombre;
    }

    public void setSerNombre(String serNombre) {
        this.serNombre = serNombre;
    }

    public double getSerPrecio() {
        return serPrecio;
    }

    public void setSerPrecio(double serPrecio) {
        this.serPrecio = serPrecio;
    }

    public char getSerIva() {
        return serIva;
    }

    public void setSerIva(char serIva) {
        this.serIva = serIva;
    }

    public char getSerEstado() {
        return serEstado;
    }

    public void setSerEstado(char serEstado) {
        this.serEstado = serEstado;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "serId=" + serId +
                ", serNombre='" + serNombre + '\'' +
                ", serPrecio=" + serPrecio +
                ", serIva='" + serIva + '\'' +
                ", serEstado='" + serEstado + '\'' +
                '}';
    }
}
