package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.*;

public class ButtonWorker {

    Cliente clienteSeleccionado;
    Servicio servicioSeleccionado;
    Empleado empleadoSeleccionado;
    boolean atencion = false;

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

        vista.getBoton_agregar_empleado().setOnAction(e -> agregarEmpleado());
        vista.getBoton_eliminar_empleado().setOnAction(e -> eliminarEmpleado());
        vista.getBoton_guardar_empleado().setOnAction(e -> guardarEmpleado());
        vista.getBoton_modificar_empleado().setOnAction(e -> modificarEmpleado());
        vista.getBoton_limpiar_campos_empleado().setOnAction(e -> limpiarCamposEmpleado());
        vista.getCombo_empleado_tipo().setOnAction(e -> escuchadorComboTipo());
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
            Persona personaCliente = vista.controlador.empj.encontrarPersonaPorCedula(vista.getPersonas(), cedulaCliente);

            Cliente cliente = new Cliente('A', personaCliente);
            boolean agregarCliente = vista.controlador.agregarCliente(cliente);
            if (agregarCliente) {
                vista.message.successMessage("Cliente creado Correctamente!");
                vista.setPersonas(vista.controlador.obtenerPersonas());
                vista.setClientes(vista.controlador.obtenerClientes(vista.getPersonas()));
                vista.tableWorker.setDataClientes();
                vista.tableWorker.setDataClientesCitas();
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
            vista.tableWorker.setDataClientesCitas();
            vista.tableWorker.setDataProximasCitas();
            vista.tableWorker.setDataCancelarCitas();
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
                vista.message.successMessage("Cliente eliminado o desactivado Correctamente!");
                vista.setClientes(vista.controlador.obtenerClientes(vista.getPersonas()));
                vista.tableWorker.setDataClientes();
                vista.tableWorker.setDataClientesCitas();
            } else {
                vista.message.errorMessage("Error al eliminar o desactivar Cliente.");
            }
        } else {
            vista.message.successMessage("Eliminar cliente cancelado.");
        }
    }

    public void limpiarCamposClientes() {
        vista.getBoton_agregar_cliente().setDisable(false);
        vista.getBoton_guardar_clientes().setDisable(true);

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
                boolean agregar = vista.controlador.agregarServicio(servicio);
                if (agregar) {
                    vista.message.successMessage("Servicio creado Correctamente!");
                    vista.setServicios(vista.controlador.obtenerServicios());
                    vista.tableWorker.setDataServicios();
                    limpiarCamposServicio();
                } else {
                    vista.message.errorMessage("Error al crear Servicio.");
                }
            } catch (NullPointerException e) {
                vista.message.errorMessage("Seleccionar si el producto tiene IVA.");
            } catch (NumberFormatException e) {
                vista.message.errorMessage("Ingresar un precio válido.");
            }
        }

    }

    public void modificarServicio() {
        servicioSeleccionado = vista.getTabla_servicios().getItems().get(vista.indiceServicios);

        vista.getServicios_nombre().setText(servicioSeleccionado.getSerNombre());
        vista.getServicios_precio().setText(String.valueOf(servicioSeleccionado.getSerPrecio()));
        String tiene_iva = servicioSeleccionado.getSerIva() == 'S' ? "Si" : "No";
        vista.getCombo_servicios_iva().setValue(tiene_iva);

        vista.getBoton_agregar_servicio().setDisable(true);
        vista.getBoton_guardar_servicio().setDisable(false);
    }

    public void guardarServicio() {
        String nombreServicio = vista.getServicios_nombre().getText();
        String precioServicio = vista.getServicios_precio().getText();
        String tiene_iva = "";

        if (nombreServicio.isEmpty() || precioServicio.isEmpty()) {
            vista.message.errorMessage("No se puede modificar al servicio con valores inválidos.");
        } else {
            try {
                if (vista.getCombo_servicios_iva().getValue().equals("Si")) {
                    tiene_iva = "Si";
                } else if (vista.getCombo_servicios_iva().getValue().equals("No")) {
                    tiene_iva = "No";
                }
                char iva = tiene_iva.equals("Si") ? 'S' : 'N';

                servicioSeleccionado = vista.getTabla_servicios().getItems().get(vista.indiceServicios);

                servicioSeleccionado.setSerNombre(nombreServicio);
                servicioSeleccionado.setSerPrecio(Double.parseDouble(precioServicio));
                servicioSeleccionado.setSerIva(iva);

                boolean ejecutar = vista.controlador.modificarServicio(servicioSeleccionado);

                if (ejecutar) {
                    vista.setServicios(vista.controlador.obtenerServicios());
                    vista.message.successMessage("Servicio modificado Correctamente!");
                    vista.tableWorker.setDataServicios();
                } else {
                    vista.message.errorMessage("Error al modificar Servicio.");
                }

            } catch (NullPointerException e) {
                vista.message.errorMessage("Seleccionar si el producto tiene IVA.");
            } catch (NumberFormatException e) {
                vista.message.errorMessage("Ingresar un precio válido.");
            }
        }

        vista.getBoton_agregar_servicio().setDisable(false);
        vista.getBoton_guardar_servicio().setDisable(true);
        limpiarCamposServicio();

    }

    public void eliminarServicio() {
        servicioSeleccionado = vista.getTabla_servicios().getItems().get(vista.indiceServicios);

        boolean confirmar = vista.message.confirmationMessage("Estas seguro que quieres eliminar el Servicio?");
        if (confirmar) {
            boolean ejecutar = vista.controlador.eliminarServicio(servicioSeleccionado.getSerId());
            if (ejecutar) {
                vista.message.successMessage("Servicio eliminado o editado Correctamente!");
                vista.setServicios(vista.controlador.obtenerServicios());
                vista.tableWorker.setDataServicios();
            } else {
                vista.message.errorMessage("Error al eliminar o editar Servicio.");
            }
        } else {
            vista.message.successMessage("Eliminar servicio cancelado.");
        }
        limpiarCamposServicio();
    }

    public void limpiarCamposServicio() {
        vista.getBoton_agregar_servicio().setDisable(false);
        vista.getBoton_guardar_servicio().setDisable(true);

        vista.getServicios_nombre().setText("");
        vista.getServicios_precio().setText("");
    }

    public void agregarEmpleado() {
        String nombre = vista.getEmpleado_nombre().getText();
        String apellido = vista.getEmpleado_apellidos().getText();
        String cedula = vista.getEmpleado_cedula().getText();
        String correo = vista.getEmpleado_correo().getText();
        String direccion = vista.getEmpleado_direccion().getText();
        String telefono = vista.getEmpleado_telefono().getText();
        String nombreTipo = "";
        String nombrePermiso = "";
        String userName = "";
        String passwordUser = "";
        if (atencion) {
            userName = vista.getEmpleado_usuario().getText();
            passwordUser = vista.getEmpleado_contrasena().getText();
        }

        Persona persona = new Persona(cedula, nombre, apellido, direccion, telefono, correo);
        boolean agregarPersona = vista.controlador.agregarPersona(persona);

        if (agregarPersona) {
            try {
                vista.setPersonas(vista.controlador.obtenerPersonas());
                Persona personaCliente = vista.controlador.empj.encontrarPersonaPorCedula(vista.getPersonas(), cedula);

                if (vista.getCombo_empleado_tipo().getValue().equals("Atencion al Cliente")) {
                    nombreTipo = "Atencion al Cliente";
                } else if (vista.getCombo_empleado_tipo().getValue().equals("Odontologo")) {
                    nombreTipo = "Odontologo";
                }
                Tipo tipo = vista.controlador.obtenerTipoPorNombre(nombreTipo);
                if (tipo != null) {
                    Empleado empleado = new Empleado(personaCliente, tipo);
                    boolean crearEmpleado = vista.controlador.agregarEmpleado(empleado);

                    if (crearEmpleado) {
                        vista.message.successMessage("Empleado creado correctamente.");
                        vista.setEmpleados(vista.controlador.obtenerEmpleados(vista.getPersonas(), vista.controlador.obtenerTipos()));
                        vista.tableWorker.setDataEmpleados();
                        vista.tableWorker.setDataEmpleadosCitas();

                        Empleado empleadoEmp = vista.controlador.empj.encontrarEmpleadoPorCedula(vista.getEmpleados(), cedula);

                        if (atencion) {
                            if (vista.getCombo_empleado_permiso().getValue().equals("Administrador")) {
                                nombrePermiso = "Administrador";
                            } else if (vista.getCombo_empleado_permiso().getValue().equals("General")) {
                                nombrePermiso = "General";
                            }

                            Permiso permiso = vista.controlador.obtenerPermisoPorNombre(nombrePermiso);
                            Usuario usuario = new Usuario(userName, passwordUser, empleadoEmp, permiso);

                            boolean crearUsuario = vista.controlador.agregarUsuario(usuario);

                            if (crearUsuario) {
                                vista.message.successMessage("Usuario creado correctamente.");
                                vista.setUsuarios(vista.controlador.obtenerUsuarios(
                                        vista.controlador.obtenerEmpleados(vista.controlador.obtenerPersonas(),
                                                vista.controlador.obtenerTipos()), vista.controlador.obtenerPermisos()));
                            } else {
                                vista.message.errorMessage("Error al crear usuario.");
                            }
                        }
                        limpiarCamposEmpleado();
                    } else {
                        vista.message.errorMessage("Error al crear empleado.");
                    }
                    System.out.println("empleado = " + empleado);
                } else {
                    vista.message.errorMessage("Error al obtener el tipo.");
                }
            } catch (NullPointerException e) {
                vista.message.errorMessage("Seleccionar el tipo o permiso de empleado.");
            }
        }
    }

    public void modificarEmpleado() {
        vista.getBoton_agregar_empleado().setDisable(true);
        vista.getCombo_empleado_tipo().setDisable(true);
        vista.getBoton_guardar_empleado().setDisable(false);
        empleadoSeleccionado = vista.getTabla_empleados().getItems().get(vista.indiceEmpleados);

        vista.getEmpleado_nombre().setText(empleadoSeleccionado.getPersona().getPerNombre());
        vista.getEmpleado_apellidos().setText(empleadoSeleccionado.getPersona().getPerApellido());
        vista.getEmpleado_cedula().setText(empleadoSeleccionado.getPersona().getPerCedula());
        vista.getEmpleado_correo().setText(empleadoSeleccionado.getPersona().getPerCorreoElectronico());
        vista.getEmpleado_direccion().setText(empleadoSeleccionado.getPersona().getPerDireccion());
        vista.getEmpleado_telefono().setText(empleadoSeleccionado.getPersona().getPerTelefono());
        String tipo = empleadoSeleccionado.getTipo().getTipNombre().equals("Odontologo") ? "Odontologo" : "Atencion al Cliente";
        vista.getCombo_empleado_tipo().setValue(tipo);
        if (tipo.equals("Atencion al Cliente")) {
            vista.getEmpleado_usuario().setDisable(false);
            vista.getCombo_empleado_permiso().setDisable(false);
            vista.getEmpleado_contrasena().setDisable(false);

            Usuario user = vista.controlador.empj.encontrarUsuarioPorCedula(vista.getUsuarios(), empleadoSeleccionado.getPersona().getPerCedula());
            vista.getEmpleado_usuario().setText(user.getUsrNombre());
            vista.getEmpleado_contrasena().setText(user.getUsrContrasenia());
            String permiso = user.getPermiso().getPrmTipo().equals("General") ? "General" : "Administrador";
            vista.getCombo_empleado_permiso().setValue(permiso);
        }
    }

    public void guardarEmpleado() {
        boolean modificarUsuario = false;

        String nombre = vista.getEmpleado_nombre().getText();
        String apellido = vista.getEmpleado_apellidos().getText();
        String cedula = vista.getEmpleado_cedula().getText();
        String correo = vista.getEmpleado_correo().getText();
        String direccion = vista.getEmpleado_direccion().getText();
        String telefono = vista.getEmpleado_telefono().getText();
        String nombrePermiso = "";
        Usuario newuUsuario = null;

        empleadoSeleccionado = vista.getTabla_empleados().getItems().get(vista.indiceEmpleados);
        empleadoSeleccionado.getPersona().setPerCedula(cedula);
        empleadoSeleccionado.getPersona().setPerNombre(nombre);
        empleadoSeleccionado.getPersona().setPerApellido(apellido);
        empleadoSeleccionado.getPersona().setPerCorreoElectronico(correo);
        empleadoSeleccionado.getPersona().setPerDireccion(direccion);
        empleadoSeleccionado.getPersona().setPerTelefono(telefono);

        boolean modifiarPersona = vista.controlador.modificarPersona(empleadoSeleccionado.getPersona());

        if (empleadoSeleccionado.getTipo().getTipNombre().equals("Atencion al Cliente")) {
            vista.getEmpleado_usuario().setDisable(false);
            vista.getCombo_empleado_permiso().setDisable(false);
            vista.getEmpleado_contrasena().setDisable(false);

            modificarUsuario = true;
            String userName = vista.getEmpleado_usuario().getText();
            String passwordUser = vista.getEmpleado_contrasena().getText();

            if (vista.getCombo_empleado_permiso().getValue().equals("Administrador")) {
                nombrePermiso = "Administrador";
            } else if (vista.getCombo_empleado_permiso().getValue().equals("General")) {
                nombrePermiso = "General";
            }

            Permiso permiso = vista.controlador.obtenerPermisoPorNombre(nombrePermiso);

            newuUsuario = vista.controlador.empj.encontrarUsuarioPorCedula(vista.getUsuarios(), cedula);
            newuUsuario.setPermiso(permiso);
            newuUsuario.setUsrNombre(userName);
            newuUsuario.setUsrContrasenia(passwordUser);
        }

        if (modifiarPersona) {
            if (modificarUsuario && (newuUsuario != null)) {
                boolean modifcar = vista.controlador.modificarUsuario(newuUsuario);
                if (modifcar) {
                    vista.message.successMessage("Usuario modificado Correctamente!");
                } else {
                    vista.message.errorMessage("Error al modificar Usuario.");
                }
            }
            vista.message.successMessage("Empleado modificado Correctamente!");
            vista.setPersonas(vista.controlador.obtenerPersonas());
            vista.setEmpleados(vista.controlador.obtenerEmpleados(vista.getPersonas(), vista.controlador.obtenerTipos()));
            vista.setUsuarios(vista.controlador.obtenerUsuarios(
                    vista.controlador.obtenerEmpleados(vista.controlador.obtenerPersonas(),
                            vista.controlador.obtenerTipos()), vista.controlador.obtenerPermisos()));
            vista.tableWorker.setDataEmpleados();
            vista.tableWorker.setDataEmpleadosCitas();
            vista.tableWorker.setDataProximasCitas();
            vista.tableWorker.setDataCancelarCitas();
        } else {
            vista.message.errorMessage("Error al modificar Empleado.");
        }

        vista.getBoton_agregar_empleado().setDisable(false);
        vista.getBoton_guardar_empleado().setDisable(true);
        vista.getCombo_empleado_tipo().setDisable(false);

        vista.getEmpleado_usuario().setDisable(true);
        vista.getCombo_empleado_permiso().setDisable(true);
        vista.getEmpleado_contrasena().setDisable(true);
        atencion = false;
        limpiarCamposEmpleado();
    }

    public void eliminarEmpleado() {
        empleadoSeleccionado = vista.getTabla_empleados().getItems().get(vista.indiceEmpleados);

        boolean confirmar = vista.message.confirmationMessage("Estas seguro que quieres eliminar al Empleado?");

        if (confirmar) {
            boolean ejecutar = vista.controlador.eliminarEmpleado(empleadoSeleccionado);
            if (ejecutar) {
                vista.message.successMessage("Empleado eliminado Correctamente!");
                vista.setPersonas(vista.controlador.obtenerPersonas());
                vista.setEmpleados(vista.controlador.obtenerEmpleados(vista.getPersonas(), vista.controlador.obtenerTipos()));
                vista.setUsuarios(vista.controlador.obtenerUsuarios(
                        vista.controlador.obtenerEmpleados(vista.controlador.obtenerPersonas(),
                                vista.controlador.obtenerTipos()), vista.controlador.obtenerPermisos()));
                vista.tableWorker.setDataEmpleados();
                vista.tableWorker.setDataEmpleadosCitas();
            } else {
                vista.message.errorMessage("Error al eliminar Empleado.");
            }
        } else {
            vista.message.successMessage("Eliminar Empleado cancelado.");
        }
    }

    public void limpiarCamposEmpleado() {
        vista.getBoton_agregar_empleado().setDisable(false);
        vista.getBoton_guardar_empleado().setDisable(true);

        vista.getEmpleado_nombre().setText("");
        vista.getEmpleado_apellidos().setText("");
        vista.getEmpleado_cedula().setText("");
        vista.getEmpleado_correo().setText("");
        vista.getEmpleado_direccion().setText("");
        vista.getEmpleado_telefono().setText("");
        vista.getEmpleado_usuario().setText("");
        vista.getEmpleado_contrasena().setText("");
        vista.getEmpleado_usuario().setDisable(true);
        vista.getCombo_empleado_permiso().setDisable(true);
        vista.getEmpleado_contrasena().setDisable(true);
        atencion = false;

    }

    public void escuchadorComboTipo() {
        if (vista.getCombo_empleado_tipo().getValue().equals("Atencion al Cliente")) {
            vista.getEmpleado_usuario().setDisable(false);
            vista.getCombo_empleado_permiso().setDisable(false);
            vista.getEmpleado_contrasena().setDisable(false);
            atencion = true;
        } else {
            vista.getEmpleado_usuario().setText("");
            vista.getEmpleado_contrasena().setText("");

            vista.getEmpleado_usuario().setDisable(true);
            vista.getCombo_empleado_permiso().setDisable(true);
            vista.getEmpleado_contrasena().setDisable(true);
            atencion = false;
        }
    }

}
