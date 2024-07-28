package com.cultodeportivo.Modelos;

import com.cultodeportivo.proyecto_fbd.GlobalValues;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class FacturaCabecera {
    private int cabId;
    private LocalDateTime cabFecha;
    private double cabSubtotal;
    private double cabTotalIva;
    private double cabTotal;
    private Cliente cliente; 
    private Usuario usuario; 
    
    public FacturaCabecera(int cabId, LocalDateTime cabFecha, double cabSubtotal, double cabTotalIva, double cabTotal, Cliente cliente, Usuario usuario) {
        this.cabId = cabId;
        this.cabFecha = cabFecha;
        this.cabSubtotal = cabSubtotal;
        this.cabTotalIva = cabTotalIva;
        this.cabTotal = cabTotal;
        this.cliente = Objects.requireNonNull(cliente, "El objeto 'cliente' no puede ser nulo");
        this.usuario = Objects.requireNonNull(usuario, "El objeto 'usuario' no puede ser nulo");
    }

    public FacturaCabecera(LocalDateTime fechaActual, Usuario usuario) {
        this.usuario = usuario;
        this.cabFecha = fechaActual;
        this.cabSubtotal = 0;
        this.cabTotalIva = 0;
        this.cabTotal = 0;
    }
    
    public FacturaCabecera(Cliente cliente, Usuario usuario) {
        this.cliente =cliente;
        this.usuario = usuario;
    }
    
    public int getCabId() {
        return cabId;
    }

    public void setCabId(int cabId) {
        this.cabId = cabId;
    }

    public LocalDateTime getCabFecha() {
        return cabFecha;
    }

    public void setCabFecha(LocalDateTime cabFecha) {
        this.cabFecha = cabFecha;
    }

    public double getCabSubtotal() {
        return cabSubtotal;
    }

    public void setCabSubtotal(double cabSubtotal) {
        this.cabSubtotal = cabSubtotal;
    }

    public double getCabTotalIva() {
        return cabTotalIva;
    }

    public void setCabTotalIva(double cabTotalIva) {
        this.cabTotalIva = cabTotalIva;
    }

    public double getCabTotal() {
        return cabTotal;
    }

    public void setCabTotal(double cabTotal) {
        this.cabTotal = cabTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (this.cliente!= null) {
            this.cliente.setCliId(cabId); 
        }
        this.cliente = Objects.requireNonNull(cliente, "El objeto 'cliente' no puede ser nulo");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (this.usuario!= null) {
            this.usuario.setUsrId(cabId); 
        }
        this.usuario = Objects.requireNonNull(usuario, "El objeto 'usuario' no puede ser nulo");
    }

    @Override
    public String toString() {
        return "FacturaCabecera{" +
                "cabId=" + cabId +
                ", cabFecha=" + cabFecha +
                ", cabSubtotal=" + cabSubtotal +
                ", cabTotalIva=" + cabTotalIva +
                ", cabTotal=" + cabTotal +
                ", cliente=" + cliente.getCliId() +
                ", usuario=" + usuario.getUsrId() +
                '}';
    }
    
    public void actualizarData(ArrayList<FacturaDetalle> detalles){
        this.calcularIva(detalles);
        this.calcularSubtotal(detalles);
        this.calcularTotal(detalles);
    }
    
    private void calcularSubtotal(ArrayList<FacturaDetalle> detalles){
        this.cabSubtotal = 0;
        for (FacturaDetalle detalle : detalles) {
            this.cabSubtotal += detalle.getDetSubtotal();
        }
        this.cabSubtotal = GlobalValues.roundToTwoDecimals(cabSubtotal);
    }
    
    private void calcularIva(ArrayList<FacturaDetalle> detalles){
        this.cabTotalIva = 0;
        for (FacturaDetalle detalle : detalles) {
            this.cabTotalIva += detalle.getDetIva();
        }
        this.cabTotalIva = GlobalValues.roundToTwoDecimals(cabTotalIva);
    }
    
    private void calcularTotal(ArrayList<FacturaDetalle> detalles){
        this.cabTotal = 0;
        for (FacturaDetalle detalle : detalles) {
            this.cabTotal += detalle.getDetTotal();
        }
        this.cabTotal = GlobalValues.roundToTwoDecimals(cabTotal);
    }
}
