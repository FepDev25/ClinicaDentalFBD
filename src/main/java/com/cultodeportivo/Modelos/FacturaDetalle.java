package com.cultodeportivo.Modelos;

import com.cultodeportivo.proyecto_fbd.GlobalValues;
import java.util.Objects;

public class FacturaDetalle {
    private int detId;
    private double detPrecioUnitario;
    private double detSubtotal;
    private double detIva;
    private int detCantidad;
    private Servicio servicio;
    private FacturaCabecera facturaCabecera; 
    private double detTotal;
    
    public FacturaDetalle(int detId, double total, double detSubtotal, double detIva, int detCantidad, Servicio servicio, FacturaCabecera facturaCabecera) {
        this.detId = detId;
        this.detSubtotal = detSubtotal;
        this.detIva = detIva;
        this.detTotal = total;
        this.detCantidad = detCantidad;
        this.servicio = Objects.requireNonNull(servicio, "El objeto 'servicio' no puede ser nulo");
        this.facturaCabecera = Objects.requireNonNull(facturaCabecera, "El objeto 'facturaCabecera' no puede ser nulo");
        this.detPrecioUnitario = servicio.getSerPrecio();
    }
    
    public FacturaDetalle(int detCantidad, Servicio servicio, FacturaCabecera facturaCabecera) {
        this.detCantidad = detCantidad;
        this.servicio = Objects.requireNonNull(servicio, "El objeto 'servicio' no puede ser nulo");
        this.facturaCabecera = Objects.requireNonNull(facturaCabecera, "El objeto 'facturaCabecera' no puede ser nulo");
        calcularAtributos();
    }
    
    public FacturaDetalle(Servicio servicio, FacturaCabecera facturaCabecera) {
        this.servicio = Objects.requireNonNull(servicio, "El objeto 'servicio' no puede ser nulo");
        this.facturaCabecera = Objects.requireNonNull(facturaCabecera, "El objeto 'facturaCabecera' no puede ser nulo");
    }
    
    private void calcularAtributos(){
        double valor_iva = GlobalValues.iva.getImpValor();
        double porcentaje = valor_iva / 100;
        
        this.detPrecioUnitario = servicio.getSerPrecio();
        
        double subtotal = this.detPrecioUnitario * this.detCantidad;
        double valor_iva_detalle = subtotal * porcentaje;
        double total = subtotal + valor_iva_detalle;
        
        this.detIva = this.servicio.getSerIva() == 'S' ? (valor_iva_detalle) : 0;
        this.detSubtotal = subtotal;
        this.detTotal = this.detIva != 0 ? total : subtotal; 
        
        this.detPrecioUnitario = GlobalValues.roundToTwoDecimals(detPrecioUnitario);
        this.detIva = GlobalValues.roundToTwoDecimals(detIva);
        this.detSubtotal = GlobalValues.roundToTwoDecimals(detSubtotal);
        this.detTotal = GlobalValues.roundToTwoDecimals(detTotal);
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

    public double getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(double detTotal) {
        this.detTotal = detTotal;
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
                ", detTotal=" + detTotal +
                '}';
    }
}
