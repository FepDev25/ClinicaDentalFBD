package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.Cita;
import com.cultodeportivo.Modelos.Cliente;
import com.cultodeportivo.Modelos.Empleado;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ButtonCitas {

    Empleado doctorSeleccionado;
    Cliente clienteSeleccionado;
    Cita citaCreada = null;
    Cita citaCancelar;
    LocalDate fechaActual = LocalDate.now();

    SecondaryController vista;

    public ButtonCitas(SecondaryController vista) {
        this.vista = vista;
    }

    void asignarAcciones() {
        vista.getButton_seleccionar_odontologo_citas().setOnAction(e -> vista.getSeleccionar_doctor().setVisible(true));
        vista.getBoton_cancelar_seleccion_doctor().setOnAction(e -> vista.getSeleccionar_doctor().setVisible(false));
        vista.getBoton_aceptar_seleccion_doctor().setOnAction(e -> incluirDoctorSeleccionado());

        vista.getButton_seleccionar_cliente_citas().setOnAction(e -> vista.getSeleccionar_cliente_citas().setVisible(true));
        vista.getBoton_cancelar_seleccion_cliente_citas().setOnAction(e -> vista.getSeleccionar_cliente_citas().setVisible(false));
        vista.getBoton_aceptar_seleccion_cliente_citas().setOnAction(e -> incluirClienteSeleccionado());

        vista.getBoton_agendar_cita().setOnAction(e -> crearCita());

        vista.getBoton_cancelar_cita_accion().setOnAction(e -> cancelarCita());
        vista.getBoton_activar_cita().setOnAction(e -> activarCita());
    }

    public void incluirDoctorSeleccionado() {
        doctorSeleccionado = vista.getTabla_seleccionar_doctor().getItems().get(vista.indiceDoctoresCitas);

        String nombre = doctorSeleccionado.getPersona().getPerNombre() + " " + doctorSeleccionado.getPersona().getPerApellido();
        vista.getText_seleccionar_odontologo_citas().setText(nombre);
        vista.getSeleccionar_doctor().setVisible(false);
    }

    public void incluirClienteSeleccionado() {
        clienteSeleccionado = vista.getTabla_seleccionar_cliente_citas().getItems().get(vista.indiceClientesCitas);

        String nombre = clienteSeleccionado.getPersona().getPerNombre() + " " + clienteSeleccionado.getPersona().getPerApellido();
        vista.getText_seleccionar_cliente_citas().setText(nombre);
        vista.getSeleccionar_cliente_citas().setVisible(false);
    }

    public void crearCita() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        try {
            LocalDate fechaSeleccionada = vista.getReserva_fecha().getValue();

            if (fechaSeleccionada != null && fechaSeleccionada.isBefore(fechaActual)) {
                vista.message.errorMessage("Error: La fecha no puede ser anterior a hoy.");
            } else {
                int hora = Integer.parseInt(vista.getReserva_label_hora().getText());
                int minuto = Integer.parseInt(vista.getReserva_label_minutos().getText());

                LocalDateTime localDateTime = fechaSeleccionada.atTime(hora, minuto);

                if (localDateTime.isBefore(fechaHoraActual)) {
                    vista.message.errorMessage("Error: La hora no puede ser anterior a la hora actual.");
                } else {
                    citaCreada = new Cita(localDateTime, 'A', doctorSeleccionado, clienteSeleccionado);

                    boolean validarCita = vista.controlador.validarDisponibilidadCita(citaCreada, vista.controlador.obtenerCitas(), doctorSeleccionado);

                    if (validarCita) {
                        boolean agregarCita = vista.controlador.agregarCita(citaCreada);
                        if (agregarCita) {
                            vista.message.successMessage("Cita Creada Correctamente!");
                            limpiarCamposCita();
                            vista.setCitas(vista.controlador.obtenerCitas());
                            vista.tableWorker.setDataProximasCitas();
                            vista.tableWorker.setDataCancelarCitas();
                        } else {
                            vista.message.errorMessage("Error al crear la cita.");
                        }
                    } else {
                        vista.message.errorMessage("Error: El Doctor tiene otra cita agendada a esa hora.");
                    }
                }

            }
        } catch (NumberFormatException e) {
            vista.message.errorMessage("Error: Ingresar una fecha válida.");
        } catch (NullPointerException e) {
            vista.message.errorMessage("Error: Seleccione Odontologo o Cliete.");
        }

    }

    public void cancelarCita() {
        try {
            citaCancelar = vista.getTabla_cancelar_citas().getItems().get(vista.indiceCitaEliminada);
            boolean desactivar = vista.controlador.desactivarCita(citaCancelar.getCitId());
            if (desactivar) {
                vista.message.successMessage("Cita Modificada Correctamente!");
                vista.setCitas(vista.controlador.obtenerCitas());
                vista.tableWorker.setDataProximasCitas();
                vista.tableWorker.setDataCancelarCitas();
            } else {
                vista.message.errorMessage("Error al modificar Cita.");
            }
        } catch (NullPointerException e) {
            vista.message.errorMessage("Error: Seleccionar una cita.");
        }

    }
    
    public void activarCita(){
        try {
            citaCancelar = vista.getTabla_cancelar_citas().getItems().get(vista.indiceCitaEliminada);
            boolean activar = vista.controlador.activarCita(citaCancelar.getCitId());
            if (activar) {
                vista.message.successMessage("Cita Modificada Correctamente!");
                vista.setCitas(vista.controlador.obtenerCitas());
                vista.tableWorker.setDataProximasCitas();
                vista.tableWorker.setDataCancelarCitas();
            } else {
                vista.message.errorMessage("Error al modificar Cita.");
            }
        } catch (NullPointerException e) {
            vista.message.errorMessage("Error: Seleccionar una cita.");
        }
    }

    public void limpiarCamposCita() {
        vista.getReserva_label_hora().setText("");
        vista.getReserva_label_minutos().setText("");
        vista.getText_seleccionar_odontologo_citas().setText("");
        vista.getText_seleccionar_cliente_citas().setText("");
        doctorSeleccionado = null;
        clienteSeleccionado = null;
    }

}
