package com.cultodeportivo.Control;

import com.cultodeportivo.Modelos.*;

public class Test {
    public static void main(String[] args) {
        Persona p = new Persona(14, "0107018091", "Karen", "Peralta", "Cuartel Davalos", "2875755", "karen@ejemplo.com");
        OperacionesEscritura op = new OperacionesEscritura();
        
        Empleado emp = new Empleado (7, p, new Tipo(1,"Odontologo"));
        
        
        Usuario user = new Usuario(0, "KPeralta", "12345", emp, new Permiso(2, "General"));
        op.CrearUsuario(user);
    }
}
