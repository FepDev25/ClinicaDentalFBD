package com.cultodeportivo.Modelos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cita {
    private int citId;
    private LocalDateTime citFecha;
    private char citEstado;
    private Empleado empleado; 
    private Cliente cliente; 

    // Constructor
    public Cita(int citId, LocalDateTime citFecha, char citEstado, Empleado empleado, Cliente cliente) {
        this.citId = citId;
        this.citFecha = citFecha;
        this.citEstado = citEstado;
        this.empleado = Objects.requireNonNull(empleado, "El objeto 'empleado' no puede ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "El objeto 'cliente' no puede ser nulo");
    }

    public int getCitId() {
        return citId;
    }

    public void setCitId(int citId) {
        this.citId = citId;
    }

    public LocalDateTime getCitFecha() {
        return citFecha;
    }

    public void setCitFecha(LocalDateTime citFecha) {
        this.citFecha = citFecha;
    }

    public char getCitEstado() {
        return citEstado;
    }

    public void setCitEstado(char citEstado) {
        this.citEstado = citEstado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        if (this.empleado!= null) {
            this.empleado.setEmpId(citId);
        }
        this.empleado = Objects.requireNonNull(empleado, "El objeto 'empleado' no puede ser nulo");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (this.cliente!= null) {
            this.cliente.setCliId(citId); 
        }
        this.cliente = Objects.requireNonNull(cliente, "El objeto 'cliente' no puede ser nulo");
    }

    @Override
    public String toString() {
        return "Cita{" +
                "citId=" + citId +
                ", citFecha=" + citFecha +
                ", citEstado='" + citEstado + '\'' +
                ", empleado=" + empleado +
                ", cliente=" + cliente +
                '}';
    }
}
