package com.cultodeportivo.Control;

import java.util.ArrayList;
import com.cultodeportivo.Modelos.*;
import com.cultodeportivo.proyecto_fbd.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Control {

    private OperacionesAcceso operacionesAcceso;
    private OperacionesEscritura operacionesEscritura;
    private OperacionesEliminar operacionesEliminar;
    private OperacionesActualizar operacionesModificar;
    public PrimaryController controller;
    public SecondaryController controller2;
    public ValidarIngresos validar;
    public Emparejador empj;

    public Control(PrimaryController controller) {
        this.operacionesAcceso = new OperacionesAcceso();
        this.operacionesEscritura = new OperacionesEscritura();
        this.controller = controller;
    }

    public Control(SecondaryController controller2) {
        this.operacionesAcceso = new OperacionesAcceso();
        this.operacionesEscritura = new OperacionesEscritura();
        this.operacionesEliminar = new OperacionesEliminar();
        this.operacionesModificar = new OperacionesActualizar();
        this.controller2 = controller2;
        this.empj = new Emparejador();
    }

    public ArrayList<Permiso> obtenerPermisos() {
        return operacionesAcceso.obtenerPermisos();
    }

    public ArrayList<Persona> obtenerPersonas() {
        return operacionesAcceso.obtenerPersonas();
    }

    public ArrayList<Tipo> obtenerTipos() {
        return operacionesAcceso.obtenerTipos();
    }

    public ArrayList<Empleado> obtenerEmpleados(ArrayList<Persona> personas, ArrayList<Tipo> tipos) {
        return operacionesAcceso.obtenerEmpleados(personas, tipos);
    }

    public ArrayList<Cliente> obtenerClientes(ArrayList<Persona> personas) {
        return operacionesAcceso.obtenerClientes(personas);
    }
    
    public ArrayList<Cita> obtenerCitas(){
        return operacionesAcceso.obtenerCitas(this.obtenerClientes(this.obtenerPersonas()), this.obtenerEmpleados(this.obtenerPersonas(), this.obtenerTipos()));
    }

    public boolean agregarPersona(Persona persona) {
        int crearPersona = operacionesEscritura.CrearPersona(persona);

        switch (crearPersona) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                controller2.message.errorMessage("La persona con esa cedula ya existe.");
                boolean crear = controller2.message.confirmationMessage("Desea crear el registro con esa persona?");
                return crear;
        }
        return false;
    }

    public boolean agregarCliente(Cliente cliente) {
        Persona persona = cliente.getPersona();
        if (persona != null) {
            operacionesEscritura.CrearCliente(cliente);
            return true;
        }
        return false;
    }

    public boolean modificarPersona(Persona persona) {
        return operacionesModificar.actualizarPersona(persona);
    }

    public boolean elimiarCliente(int id) {
        int operacion = operacionesEliminar.eliminarCliente(id);
        switch (operacion) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                boolean clienteInactivo = controller2.message.confirmationMessage("El cliente tiene facturas o citas asociadas, desea cambiar el estado del cliente a inactivo?");
                if (clienteInactivo){
                    boolean desactivar = operacionesEliminar.desactivarCliente(id);
                    return desactivar;
                }
        }
        return false;

    }

    public boolean agregarServicio(Servicio servicio) {
        return operacionesEscritura.CrearServicio(servicio);
    }

    public boolean modificarServicio(Servicio servicio) {
        return operacionesModificar.actualizarServicio(servicio);
    }

    public boolean eliminarServicio(int id) {
        int operacion = operacionesEliminar.eliminarServicio(id);
        switch (operacion) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                boolean clienteInactivo = controller2.message.confirmationMessage("El servicio tiene facturas asociadas, desea cambiar el estado del servicio a inactivo?");
                if (clienteInactivo){
                    boolean desactivar = operacionesEliminar.desactivarServicio(id);
                    return desactivar;
                }
        }
        return false;
    }

    public boolean agregarEmpleado(Empleado empleado) {
        return operacionesEscritura.CrearEmpleado(empleado);
    }

    public boolean eliminarEmpleado(Empleado empleado) {
        int operacion = operacionesEliminar.eliminarEmpleado(empleado.getEmpId());
        switch (operacion) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                if (empleado.getTipo().getTipNombre().equals("Odontologo")) {
                    controller2.message.errorMessage("El Odontologo tiene citas asociadas, no se puede eliminar.");
                } else {
                    boolean quererEliminarUsuario = controller2.message.confirmationMessage("El empleado tiene asociado un usuario. Desea eliminar el usuario?.");
                    if (quererEliminarUsuario) {
                        boolean eliminarUsuario = this.eliminarUsuario(empleado);

                        if (eliminarUsuario) {
                            int eliminar2 = operacionesEliminar.eliminarEmpleado(empleado.getEmpId());
                            return eliminar2 == 1;
                        } else {
                            return false;
                        }
                    }
                }

        }
        return false;
    }

    public boolean eliminarUsuario(Empleado empleado) {
        Usuario user = this.empj.encontrarUsuarioPorCedula(
                this.obtenerUsuarios(this.obtenerEmpleados(this.obtenerPersonas(), this.obtenerTipos()), this.obtenerPermisos()), empleado.getPersona().getPerCedula());
        int operacion2 = operacionesEliminar.eliminarUsuario(user.getUsrId());

        if (operacion2 == 1) {
            return true;
        } else if (operacion2 == 2) {
            controller2.message.errorMessage("El usuario tiene facturas asociadas, no se puede eliminar.");
        }
        return false;
    }

    public boolean agregarUsuario(Usuario usuario) {
        return operacionesEscritura.CrearUsuario(usuario);
    }

    public boolean modificarUsuario(Usuario usuario) {
        return operacionesModificar.actualizarUsuario(usuario);
    }
    
    public boolean agregarCita(Cita cita){
        return operacionesEscritura.CrearCita(cita);
    }

    public Tipo obtenerTipoPorNombre(String nombre) {
        Optional<Tipo> tipoOpt = this.obtenerTipos().stream().filter(t -> t.getTipNombre().equals(nombre)).findFirst();
        if (tipoOpt.isPresent()) {
            return tipoOpt.get();
        }
        return null;
    }

    public Permiso obtenerPermisoPorNombre(String nombre) {
        Optional<Permiso> permisoOpt = this.obtenerPermisos().stream().filter(t -> t.getPrmTipo().equals(nombre)).findFirst();
        if (permisoOpt.isPresent()) {
            return permisoOpt.get();
        }
        return null;
    }
    
    public boolean desactivarCita(int id){
        return operacionesEliminar.desactivarCita(id);
    }

    public ArrayList<Usuario> obtenerUsuarios(ArrayList<Empleado> empleados, ArrayList<Permiso> permisos) {
        return operacionesAcceso.obtenerUsuarios(empleados, permisos);
    }

    public ArrayList<Servicio> obtenerServicios() {
        return operacionesAcceso.obtenerServicios();
    }
    
    public boolean cargarListasController1() {
        controller.setPermisos(this.obtenerPermisos());
        controller.setPersonas(this.obtenerPersonas());
        controller.setTipos(this.obtenerTipos());

        if ((!controller.getPersonas().isEmpty()) && (!controller.getTipos().isEmpty())) {
            controller.setEmpleados(this.obtenerEmpleados(controller.getPersonas(), controller.getTipos()));
            if ((!controller.getEmpleados().isEmpty()) && (!controller.getPermisos().isEmpty())) {
                controller.setUsuarios(this.obtenerUsuarios(controller.getEmpleados(), controller.getPermisos()));
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean cargarListasController2() {
        controller2.setPermisos(this.obtenerPermisos());
        controller2.setPersonas(this.obtenerPersonas());
        controller2.setTipos(this.obtenerTipos());
        controller2.setServicios(this.obtenerServicios());

        if ((!controller2.getPersonas().isEmpty()) && (!controller2.getTipos().isEmpty())) {
            controller2.setEmpleados(this.obtenerEmpleados(controller2.getPersonas(), controller2.getTipos()));
            controller2.setClientes(this.obtenerClientes(controller2.getPersonas()));
            if ((!controller2.getEmpleados().isEmpty()) && (!controller2.getPermisos().isEmpty())) {
                controller2.setUsuarios(this.obtenerUsuarios(controller2.getEmpleados(), controller2.getPermisos()));
                controller2.setCitas(this.obtenerCitas());
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean validarDisponibilidadCita(Cita nuevaCita, List<Cita> citasExistente, Empleado empleado) {
        List<Cita> citasEmpleado = obtenerCitasEmpleado(citasExistente, empleado);

        for (Cita cita : citasEmpleado) {
            LocalDateTime finEstimadoCitaExistente = cita.getCitFecha().plusMinutes(30);

            if ((nuevaCita.getCitFecha().isAfter(cita.getCitFecha()) || nuevaCita.getCitFecha().isEqual(cita.getCitFecha()))
                    && nuevaCita.getCitFecha().isBefore(finEstimadoCitaExistente)) {
                return false;
            }
        }
        return true;
    }

    public List<Cita> obtenerCitasEmpleado(List<Cita> citasExistente, Empleado empleado) {
        List<Cita> citasEmpleado = new ArrayList<>();
        citasExistente.forEach(c -> {
            if (c.getEmpleado().getPersona().getPerCedula().equals(empleado.getPersona().getPerCedula())) {
                citasEmpleado.add(c);
            }
        });
        return citasEmpleado;
    }

}
