package com.cultodeportivo.Modelos;

import java.util.Objects;

public class Cliente {
    private int cliId;
    private char cliEstado;
    private Persona persona;

    // Constructor
    public Cliente(int cliId, char cliEstado, Persona persona) {
        this.cliId = cliId;
        this.cliEstado = cliEstado;
        this.persona = Objects.requireNonNull(persona, "El objeto 'persona' no puede ser nulo");
    }
    
    public Cliente(char cliEstado, Persona persona) {
        this.cliEstado = cliEstado;
        this.persona = Objects.requireNonNull(persona, "El objeto 'persona' no puede ser nulo");
    }

    public int getCliId() {
        return cliId;
    }

    public void setCliId(int cliId) {
        this.cliId = cliId;
    }

    public char getCliEstado() {
        return cliEstado;
    }

    public void setCliEstado(char cliEstado) {
        this.cliEstado = cliEstado;
    }

    public Persona getPersona() {
        return persona;
    }
    
    public void setPersona(Persona persona) {
        if (this.persona!= null) {
            this.persona.setPerId(cliId); 
        }
        this.persona = Objects.requireNonNull(persona, "El objeto 'persona' no puede ser nulo");
    }
    
    public String nombreCompleto(){
        return this.getPersona().getPerNombre() + " " + this.getPersona().getPerApellido();
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cliId=" + cliId +
                ", cliEstado='" + cliEstado + '\'' +
                ", persona=" + persona +
                '}';
    }
}
