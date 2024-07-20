package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.Cita;
import com.cultodeportivo.Modelos.Cliente;
import com.cultodeportivo.Modelos.Empleado;

public class ButtonCitas {
    Empleado doctorSeleccionado;
    Cliente clienteSeleccionado;
    Cita citaCreada;
    
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
    }
    
    public void incluirDoctorSeleccionado(){
        doctorSeleccionado = vista.getTabla_seleccionar_doctor().getItems().get(vista.indiceDoctoresCitas);
        
        String nombre = doctorSeleccionado.getPersona().getPerNombre() + " " + doctorSeleccionado.getPersona().getPerApellido();
        vista.getText_seleccionar_odontologo_citas().setText(nombre);
        vista.getSeleccionar_doctor().setVisible(false);
    }
    
    public void incluirClienteSeleccionado(){
        clienteSeleccionado = vista.getTabla_seleccionar_cliente_citas().getItems().get(vista.indiceClientesCitas);
        
        String nombre = clienteSeleccionado.getPersona().getPerNombre() + " " + clienteSeleccionado.getPersona().getPerApellido();
        vista.getText_seleccionar_cliente_citas().setText(nombre);
        vista.getSeleccionar_cliente_citas().setVisible(false);
    }
    
    public void crearCita(){
        
    }
    
    
}
