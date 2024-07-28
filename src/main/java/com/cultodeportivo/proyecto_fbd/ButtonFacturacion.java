package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.Cliente;
import com.cultodeportivo.Modelos.FacturaCabecera;
import com.cultodeportivo.Modelos.FacturaDetalle;
import com.cultodeportivo.Modelos.Servicio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ButtonFacturacion {

    FacturaCabecera cabecera;
    ArrayList<FacturaDetalle> detalles;
    Servicio servicio = null;
    Cliente cliente = null;
    LocalDateTime fechaActual = LocalDateTime.now();
    
    SecondaryController vista;
    
    public ButtonFacturacion(SecondaryController vista) {
        this.vista = vista;
        this.detalles = new ArrayList<>();
        this.cabecera = new FacturaCabecera(fechaActual);
    }
    
    public void asignarAcciones() {
        vista.getBoton_buscar_cliente().setOnAction(e -> vista.getSeleccionar_cliente_facturas().setVisible(true));
        vista.getBoton_buscar_servicio().setOnAction(e -> agregarServicioDetalle());
        vista.getBoton_cancelar_seleccion_cli_fac().setOnAction(e -> vista.getSeleccionar_cliente_facturas().setVisible(false));
        vista.getBoton_aceptar_seleccion_cli_fac().setOnAction(e -> aceptarSeleccionCliente());
        vista.getBoton_facturar().setOnAction(e -> facturar());
        
        vista.getFactura_label_fecha().setText("Fecha: " + fechaActual.format(DateTimeFormatter.ISO_DATE));
    }
    
    public void agregarServicioDetalle() {
        try {
            servicio = vista.getTable_servicios_facturas().getItems().get(vista.indiceServicioFacturacion);
            int cantidad = Integer.parseInt(vista.getLabel_seleccionar_cantidad().getText());
            
            FacturaDetalle facAux = new FacturaDetalle(cantidad, servicio, cabecera);
            this.detalles.add(facAux);
            vista.tableWorker.setDetalleFacturaDetalles(facAux);
            this.cabecera.actualizarData(detalles);
            vista.tableWorker.setDataFacturaCabecera(this.cabecera);
            vista.getLabel_visor_total().setText(String.valueOf(this.cabecera.getCabTotal()));
            System.out.println(facAux);
        } catch (NumberFormatException e) {
            vista.message.errorMessage("La cantidad debe ser numerica.");
        }
        
    }
    
    public void aceptarSeleccionCliente() {
        cliente = vista.getTabla_seleccionar_cliente_facturas().getItems().get(vista.indiceClienteFacturacion);
        
        vista.getFactura_label_apellido().setText("Apellido: " + cliente.getPersona().getPerApellido());
        vista.getFactura_label_cedula().setText("Cedula: " + cliente.getPersona().getPerCedula());
        vista.getFactura_label_correo().setText("Correo: " + cliente.getPersona().getPerCorreoElectronico());
        vista.getFactura_label_direccion().setText("Direccion: " + cliente.getPersona().getPerDireccion());
        vista.getFactura_label_nombre().setText("Nombre: " + cliente.getPersona().getPerNombre());
        vista.getFactura_label_telefono().setText("Telefono: " + cliente.getPersona().getPerTelefono());
        
        vista.getSeleccionar_cliente_facturas().setVisible(false);
    }
    
    public void facturar() {
        
    }
    
}
