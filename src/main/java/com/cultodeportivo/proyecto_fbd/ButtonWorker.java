package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.*;

public class ButtonWorker {

    Cliente clienteSeleccionado;

    SecondaryController vista;

    public ButtonWorker(SecondaryController vista) {
        this.vista = vista;
    }

    public void asignarAcciones() {
        vista.getBoton_agregar_cliente().setOnAction(e -> agregarCliente());
        vista.getBoton_limpiar_campos_cliente().setOnAction(e -> limpiarCamposClientes());
        vista.getBoton_modificar_cliente().setOnAction(e -> modificarCliente());
        vista.getBoton_guardar_clientes().setOnAction(e -> guardarCliente());
        vista.getBoton_eliminar_cliente().setOnAction(e -> eliminarCliente());

        vista.getBoton_agregar_servicio().setOnAction(e -> agregarServicio());
        vista.getBoton_eliminar_servicios().setOnAction(e -> eliminarServicio());
        vista.getBoton_guardar_servicio().setOnAction(e -> guardarServicio());
        vista.getBoton_limpiar_campos_servicios().setOnAction(e -> limpiarCamposServicio());
        vista.getBoton_modificar_servicio().setOnAction(e -> modificarServicio());
    }

    public void agregarCliente() {
        String nombreCliente = vista.getCliente_nombre().getText();
        String apellidoCliente = vista.getCliente_apellidos().getText();
        String cedulaCliente = vista.getCliente_cedula().getText();
        String correoCliente = vista.getCliente_correo().getText();
        String telefonoCliente = vista.getCliente_telefono().getText();
        String direccionCliente = vista.getCliente_direccion().getText();

        Persona pAux = new Persona(cedulaCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente, correoCliente);
        boolean agregarPersona = vista.controlador.agregarPersona(pAux);

        if (agregarPersona) {
            vista.setPersonas(vista.controlador.obtenerPersonas());
            Persona personaCliente = vista.controlador.empj.encontrarClientePorCedula(vista.getPersonas(), cedulaCliente);
            System.out.println("La persona se va a buscar en: " + vista.getPersonas());
            Cliente cliente = new Cliente('A', personaCliente);
            boolean agregarCliente = vista.controlador.agregarCliente(cliente);
            if (agregarCliente) {
                vista.message.successMessage("Cliente creado Correctamente!");
                vista.setPersonas(vista.controlador.obtenerPersonas());
                vista.setClientes(vista.controlador.obtenerClientes(vista.getPersonas()));
                vista.tableWorker.setDataClientes();
                limpiarCamposClientes();
            }
        } else {
            vista.message.errorMessage("Error al crear Cliente.");
        }
    }

    public void modificarCliente() {
        clienteSeleccionado = vista.getTabla_cliente().getItems().get(vista.indiceClientes);

        vista.getCliente_nombre().setText(clienteSeleccionado.getPersona().getPerNombre());
        vista.getCliente_apellidos().setText(clienteSeleccionado.getPersona().getPerApellido());
        vista.getCliente_cedula().setText(clienteSeleccionado.getPersona().getPerCedula());
        vista.getCliente_telefono().setText(clienteSeleccionado.getPersona().getPerTelefono());
        vista.getCliente_direccion().setText(clienteSeleccionado.getPersona().getPerDireccion());
        vista.getCliente_correo().setText(clienteSeleccionado.getPersona().getPerCorreoElectronico());

        vista.getBoton_guardar_clientes().setDisable(false);
        vista.getBoton_agregar_cliente().setDisable(true);
    }

    public void guardarCliente() {
        String nombreCliente = vista.getCliente_nombre().getText();
        String apellidoCliente = vista.getCliente_apellidos().getText();
        String cedulaCliente = vista.getCliente_cedula().getText();
        String correoCliente = vista.getCliente_correo().getText();
        String telefonoCliente = vista.getCliente_telefono().getText();
        String direccionCliente = vista.getCliente_direccion().getText();

        clienteSeleccionado = vista.getTabla_cliente().getItems().get(vista.indiceClientes);
        clienteSeleccionado.getPersona().setPerCedula(cedulaCliente);
        clienteSeleccionado.getPersona().setPerNombre(nombreCliente);
        clienteSeleccionado.getPersona().setPerApellido(apellidoCliente);
        clienteSeleccionado.getPersona().setPerCorreoElectronico(correoCliente);
        clienteSeleccionado.getPersona().setPerTelefono(telefonoCliente);
        clienteSeleccionado.getPersona().setPerDireccion(direccionCliente);

        boolean ejecutar = vista.controlador.modificarPersona(clienteSeleccionado.getPersona());

        if (ejecutar) {
            vista.setPersonas(vista.controlador.obtenerPersonas());
            vista.setClientes(vista.controlador.obtenerClientes(vista.getPersonas()));
            vista.message.successMessage("Cliente modificado Correctamente!");
            vista.tableWorker.setDataClientes();
        } else {
            vista.message.errorMessage("Error al modificar Cliente.");
        }
        vista.getBoton_guardar_clientes().setDisable(true);
        vista.getBoton_agregar_cliente().setDisable(false);
        limpiarCamposClientes();

    }

    public void eliminarCliente() {
        clienteSeleccionado = vista.getTabla_cliente().getItems().get(vista.indiceClientes);

        boolean confirmar = vista.message.confirmationMessage("Estas seguro que quieres eliminar al Cliente?");

        if (confirmar) {
            boolean ejecutar = vista.controlador.elimiarCliente(clienteSeleccionado.getCliId());
            if (ejecutar) {
                vista.message.successMessage("Cliente elimina Correctamente!");
                vista.setClientes(vista.controlador.obtenerClientes(vista.getPersonas()));
                vista.tableWorker.setDataClientes();
            } else {
                vista.message.errorMessage("Error al eliminar Cliente.");
            }
        } else {
            vista.message.successMessage("Eliminar cliente cancelado.");
        }
    }

    public void limpiarCamposClientes() {
        vista.getCliente_nombre().setText("");
        vista.getCliente_apellidos().setText("");
        vista.getCliente_cedula().setText("");
        vista.getCliente_telefono().setText("");
        vista.getCliente_direccion().setText("");
        vista.getCliente_correo().setText("");
    }

    public void agregarServicio() {
        String nombreServicio = vista.getServicios_nombre().getText();
        String precioServicio = vista.getServicios_precio().getText();
        String tiene_iva = "";

        if (nombreServicio.isEmpty() || precioServicio.isEmpty()) {
            vista.message.errorMessage("Ingresar un nombre o precio válidos.");
        } else {
            try {
                if (vista.getCombo_servicios_iva().getValue().equals("Si")) {
                    tiene_iva = "Si";
                } else if (vista.getCombo_servicios_iva().getValue().equals("No")) {
                    tiene_iva = "No";
                }
                char iva = tiene_iva.equals("Si") ? 'S' : 'N';

                Servicio servicio = new Servicio(nombreServicio, Double.parseDouble(precioServicio), iva, 'A');

                System.out.println(servicio);
            } catch (NullPointerException e) {
                vista.message.errorMessage("Seleccionar si el producto tiene IVA.");
            }
        }

    }

    public void modificarServicio() {

    }

    public void guardarServicio() {

    }

    public void eliminarServicio() {

    }

    public void limpiarCamposServicio() {

    }

}
