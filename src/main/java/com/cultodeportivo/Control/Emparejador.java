package com.cultodeportivo.Control;

import com.cultodeportivo.Modelos.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Emparejador {
    
    public Empleado emparejarEmpleado(int empId, int per_id, int tip_id, ArrayList<Persona> personas, ArrayList<Tipo> tipos) {
        Optional<Persona> personaOpt = personas.stream()
               .filter(p -> p.getPerId() == per_id)
               .findFirst();

        Optional<Tipo> tipoOpt = tipos.stream()
               .filter(t -> t.getTipId() == tip_id)
               .findFirst();

        if (personaOpt.isPresent() && tipoOpt.isPresent()) {
            Persona persona = personaOpt.get();
            Tipo tipo = tipoOpt.get();
            return new Empleado(empId, persona, tipo);
        } else {
            System.out.println("Persona o Tipo no encontrado.");
            return null;
        }
    }
    
    public Cliente emparejarCliente(int cliId, char cliEstado, int perId, ArrayList<Persona> personas){
        Optional<Persona> personaOpt = personas.stream()
               .filter(p -> p.getPerId() == perId)
               .findFirst();
        
        if (personaOpt.isPresent()){
            Persona persona = personaOpt.get();
            return new Cliente(cliId, cliEstado, persona);
        } else {
            System.out.println("Persona no encontrada.");
            return null;
        }
    }
    
    public Usuario emparejarUsuarios(int usrId, String usrNombre, String usrContrasenia, int empId, int prmId, ArrayList<Empleado> empleados, ArrayList<Permiso> permisos){
        Optional<Empleado> empleadoOpt = empleados.stream()
               .filter(e -> e.getEmpId() == empId)
               .findFirst();
        
        Optional<Permiso> permisoOpt = permisos.stream()
               .filter(p -> p.getPrmId() == prmId)
               .findFirst();
        
        if (empleadoOpt.isPresent() && permisoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            Permiso permiso = permisoOpt.get();
            return new Usuario(usrId, usrNombre, usrContrasenia, empleado, permiso);
        } else {
            System.out.println("Empleado o Permiso no encontrado.");
            return null;
        }
    }
    
    public Cita emparejarCitas(int citId,  LocalDateTime citFecha, int cliId, int empId, char citEstado,  ArrayList<Empleado> empleados, ArrayList<Cliente> clientes){
        Optional<Empleado> empleadoOpt = empleados.stream()
               .filter(e -> e.getEmpId() == empId)
               .findFirst();
        
        Optional<Cliente> clienteOpt = clientes.stream()
               .filter(c -> c.getCliId() == cliId)
               .findFirst();
        
        if (empleadoOpt.isPresent() && clienteOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            Cliente cliente = clienteOpt.get();
            return new Cita(citId,  citFecha, citEstado, empleado, cliente);
        } else {
            System.out.println("Empleado o Cliente no encontrado al emparejar citas.");
            return null;
        }
    }
    
    public Persona encontrarPersonaPorCedula(ArrayList<Persona> personas, String cedula){
        Optional<Persona> personaOpt = personas.stream()
               .filter(e -> e.getPerCedula().equals(cedula))
               .findFirst();
        
        if (personaOpt.isPresent()){
            Persona persona = personaOpt.get();
            return persona;
        } else {
            System.out.println("Persona no encontrada.");
            return null;
        }
    }
    
    public Empleado encontrarEmpleadoPorCedula(ArrayList<Empleado> empleados, String cedula){
        Optional<Empleado> empleadoOpt = empleados.stream()
               .filter(e -> e.getPersona().getPerCedula().equals(cedula))
               .findFirst();
        
        if (empleadoOpt.isPresent()){
            Empleado empleado = empleadoOpt.get();
            return empleado;
        } else {
            System.out.println("Empleado no encontrado.");
            return null;
        }
    }
    
    public Usuario encontrarUsuarioPorCedula(ArrayList<Usuario> usuarios, String cedula){
        Optional<Usuario> usuarioOpt = usuarios.stream()
               .filter(e -> e.getEmpleado().getPersona().getPerCedula().equals(cedula))
               .findFirst();
        
        if (usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            return usuario;
        } else {
            System.out.println("Usuario no encontrado.");
            return null;
        }
    }
    
    public FacturaCabecera emparejarFacturaCabecera(int cli_id, int user_id, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios){
        Optional <Cliente> clienteOpt = clientes.stream()
                .filter(c -> c.getCliId() == cli_id)
                .findFirst();
        
        Optional<Usuario> usuarioOpt = usuarios.stream()
               .filter(u -> u.getUsrId() == user_id)
               .findFirst();
        
        if (usuarioOpt.isPresent() && clienteOpt.isPresent()){
            Cliente cliente = clienteOpt.get();
            Usuario usuario = usuarioOpt.get();
            return new FacturaCabecera(cliente, usuario);
        } else{
            return null;
        }
    }
    
    public FacturaDetalle emparejarFacturaDetalle(int cab_id, int serv_id, ArrayList<FacturaCabecera> cabeceras, ArrayList<Servicio> servicios){
        Optional <FacturaCabecera> cabOpt = cabeceras.stream()
                .filter(c -> c.getCabId() == cab_id)
                .findFirst();
        
        Optional<Servicio> servicioOpt = servicios.stream()
               .filter(s -> s.getSerId() == serv_id)
               .findFirst();
        
        if (cabOpt.isPresent() && servicioOpt.isPresent()){
            FacturaCabecera cabecera = cabOpt.get();
            Servicio servicio = servicioOpt.get();
            return new FacturaDetalle(servicio, cabecera);
        } else{
            return null;
        }
    }
    
    public Cliente encontrarConsumidorFinal(ArrayList<Cliente> clientes){
        String cedula = "9999999999";
        Optional<Cliente> clienteOpt = clientes.stream()
               .filter(e -> e.getPersona().getPerCedula().equals(cedula))
               .findFirst();
        
        if (clienteOpt.isPresent()){
            Cliente cliente = clienteOpt.get();
            return cliente;
        } else {
            System.out.println("Cliente no encontrada.");
            return null;
        }
    }
    
    
}
