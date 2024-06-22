package com.cultodeportivo.Modelos;

import java.util.Objects;

public class FacturaDetalle {
    private int detId;
    private double detPrecioUnitario;
    private double detSubtotal;
    private double detIva;
    private int detCantidad;
    private Servicio servicio;
    private FacturaCabecera facturaCabecera; 

    public FacturaDetalle(int detId, double detPrecioUnitario, double detSubtotal, double detIva, int detCantidad, Servicio servicio, FacturaCabecera facturaCabecera) {
        this.detId = detId;
        this.detPrecioUnitario = detPrecioUnitario;
        this.detSubtotal = detSubtotal;
        this.detIva = detIva;
        this.detCantidad = detCantidad;
        this.servicio = Objects.requireNonNull(servicio, "El objeto 'servicio' no puede ser nulo");
        this.facturaCabecera = Objects.requireNonNull(facturaCabecera, "El objeto 'facturaCabecera' no puede ser nulo");
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public double getDetPrecioUnitario() {
        return detPrecioUnitario;
    }

    public void setDetPrecioUnitario(double detPrecioUnitario) {
        this.detPrecioUnitario = detPrecioUnitario;
    }

    public double getDetSubtotal() {
        return detSubtotal;
    }

    public void setDetSubtotal(double detSubtotal) {
        this.detSubtotal = detSubtotal;
    }

    public double getDetIva() {
        return detIva;
    }

    public void setDetIva(double detIva) {
        this.detIva = detIva;
    }

    public int getDetCantidad() {
        return detCantidad;
    }

    public void setDetCantidad(int detCantidad) {
        this.detCantidad = detCantidad;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        if (this.servicio!= null) {
            this.servicio.setSerId(detId);
        }
        this.servicio = Objects.requireNonNull(servicio, "El objeto 'servicio' no puede ser nulo");
    }

    public FacturaCabecera getFacturaCabecera() {
        return facturaCabecera;
    }

    public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
        if (this.facturaCabecera!= null) {
            this.facturaCabecera.setCabId(detId); 
        }
        this.facturaCabecera = Objects.requireNonNull(facturaCabecera, "El objeto 'facturaCabecera' no puede ser nulo");
    }

    // Método toString
    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "detId=" + detId +
                ", detPrecioUnitario=" + detPrecioUnitario +
                ", detSubtotal=" + detSubtotal +
                ", detIva=" + detIva +
                ", detCantidad=" + detCantidad +
                ", servicio=" + servicio +
                ", facturaCabecera=" + facturaCabecera +
                '}';
    }
}
