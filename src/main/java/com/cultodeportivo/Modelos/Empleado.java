package com.cultodeportivo.Modelos;

import java.util.Objects;

public class Empleado {
    private int empId;
    private Persona persona;
    private Tipo tipo;

    // Constructor
    public Empleado(int empId, Persona persona, Tipo tipo) {
        this.empId = empId;
        this.persona = Objects.requireNonNull(persona, "El objeto 'persona' no puede ser nulo");
        this.tipo = Objects.requireNonNull(tipo, "El objeto 'tipo' no puede ser nulo");
    }

    // Getter y Setter para empId
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    // Getter y Setter para persona
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        if (this.persona!= null) {
            this.persona.setPerId(empId); 
        }
        this.persona = Objects.requireNonNull(persona, "El objeto 'persona' no puede ser nulo");
    }

    // Getter y Setter para tipo
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        if (this.tipo!= null) {
            this.tipo.setTipId(empId);
        }
        this.tipo = Objects.requireNonNull(tipo, "El objeto 'tipo' no puede ser nulo");
    }

    // Método toString
    @Override
    public String toString() {
        return "Empleado{" +
                "empId=" + empId +
                ", persona=" + persona +
                ", tipo=" + tipo +
                '}';
    }
}
