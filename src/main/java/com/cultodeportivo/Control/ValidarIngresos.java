package com.cultodeportivo.Control;

import com.cultodeportivo.Modelos.Persona;
import com.cultodeportivo.Modelos.Servicio;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarIngresos {
    
    public String validarPersona(Persona persona) {
        String cedula = persona.getPerCedula();
        String nombre = persona.getPerNombre();
        String apellido = persona.getPerApellido();
        String direccion = persona.getPerDireccion();
        String telefono = persona.getPerTelefono();
        String correo = persona.getPerCorreoElectronico();

        String validacionCedula = validarCedula(cedula);
        if (!validacionCedula.equals("Si")) return validacionCedula;
        String validacionNombre = validarNombre(nombre);
        if (!validacionNombre.equals("Si")) return validacionNombre;
        String validacionApellido = validarApellido(apellido);
        if (!validacionApellido.equals("Si")) return validacionApellido;
        String validacionDireccion = validarDireccion(direccion);
        if (!validacionDireccion.equals("Si")) return validacionDireccion;
        String validacionTelefono = validarTelefono(telefono);
        if (!validacionTelefono.equals("Si")) return validacionTelefono;
        String validacionCorreo = validarCorreo(correo);
        if (!validacionCorreo.equals("Si")) return validacionCorreo;

        return "Si";
    }
    
    public String validarServicio(Servicio servicio) {
        String nombreServicio = servicio.getSerNombre();
        double precioServicio = servicio.getSerPrecio();

        String validacionNombre = validarNombreServicio(nombreServicio);
        if (!validacionNombre.equals("Si")) return validacionNombre;
        String validacionPrecio = validarPrecioServicio(precioServicio);
        if (!validacionPrecio.equals("Si")) return validacionPrecio;

        return "Si";
    }
    
    // Metodo para validar Persona
    private String validarNombre(String nombre) {
        String regex = "^[A-Za-z ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        if (!matcher.matches()) return "Error: El nombre solo puede contener letras y espacios.";
        return "Si";
    }

    private String validarApellido(String apellido) {
        String regex = "^[A-Za-z ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(apellido);
        if (!matcher.matches()) return "Error: El apellido solo puede contener letras y espacios.";
        return "Si";
    }

    private String validarCedula(String cedula) {
        Pattern pattern = Pattern.compile("\\d+");
        if (cedula.equals("")) return "Error: La Cedula no puede estar vacía.";
        if (cedula.length() != 10) return "Error: La Cedula debe tener 10 dígitos.";
        Matcher matcher = pattern.matcher(cedula);
        if (!matcher.matches()) return "Error: La Cedula debe ser numérica.";
        return "Si";
    }

    private String validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        if (!matcher.matches()) return "Error: Correo inválido.";
        return "Si";
    }

    private String validarDireccion(String direccion) {
        String regex = "^[A-Za-z0-9., ]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(direccion);
        if (!matcher.matches()) return "Error: La dirección puede contener letras, números, espacios, comas y puntos.";
        return "Si";
    }

    private String validarTelefono(String telefono) {
        String regex = "^\\d{6,10}-?\\d?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefono);
        if (!matcher.matches()) return "Error: El número de teléfono debe ser válido.";
        return "Si";
    }
    
    // Metodos para validar servicio
    private String validarNombreServicio(String nombreServicio) {
        String regex = "^[A-Za-z0-9., ]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombreServicio);
        if (!matcher.matches()) return "Error: El nombre del servicio solo puede contener letras, números, espacios, comas y puntos.";
        return "Si";
    }
    
    private String validarPrecioServicio(double precioServicio) {
        if (precioServicio <= 0) return "Error: El precio del servicio debe ser mayor que 0.";
        return "Si";
    }


}
