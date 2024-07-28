package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.Cliente;
import com.cultodeportivo.Modelos.FacturaCabecera;
import com.cultodeportivo.Modelos.FacturaDetalle;
import com.cultodeportivo.Modelos.Servicio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ButtonFacturacion {
    
    EnviarCorreo envioCorreo;
    FacturaPDF pdfGenerator;
    FacturaCabecera cabecera;
    ArrayList<FacturaDetalle> detalles;
    Servicio servicio = null;
    Cliente cliente = null;
    LocalDateTime fechaActual = LocalDateTime.now();
    
    SecondaryController vista;
    
    public ButtonFacturacion(SecondaryController vista) {
        this.vista = vista;
        this.detalles = new ArrayList<>();
        this.cabecera = new FacturaCabecera(fechaActual, GlobalValues.userApp);
        this.pdfGenerator = new FacturaPDF(cabecera);
        this.envioCorreo = new EnviarCorreo();
    }
    
    public void asignarAcciones() {
        vista.getBoton_buscar_cliente().setOnAction(e -> vista.getSeleccionar_cliente_facturas().setVisible(true));
        vista.getBoton_quitar_servicio().setOnAction(e -> quitarServicio());
        vista.getBoton_buscar_servicio().setOnAction(e -> agregarServicioDetalle());
        vista.getBoton_cancelar_seleccion_cli_fac().setOnAction(e -> vista.getSeleccionar_cliente_facturas().setVisible(false));
        vista.getBoton_aceptar_seleccion_cli_fac().setOnAction(e -> aceptarSeleccionCliente());
        vista.getBoton_facturar().setOnAction(e -> facturar());
        
        vista.getFactura_label_fecha().setText("Fecha: " + fechaActual.format(DateTimeFormatter.ISO_DATE));
        vista.getFactura_label_nro().setText("Nro. Factura: " + GlobalValues.numeroFactura);
        setConsumidorFinal();
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
            this.pdfGenerator.agregarDetalle(facAux);
        } catch (NumberFormatException e) {
            vista.message.errorMessage("La cantidad debe ser numerica.");
        }
        
    }
    
    public void aceptarSeleccionCliente() {
        cliente = vista.getTabla_seleccionar_cliente_facturas().getItems().get(vista.indiceClienteFacturacion);
        this.llenarInfoCliente(cliente);
        cabecera.setCliente(cliente);
        
        vista.getSeleccionar_cliente_facturas().setVisible(false);
    }
    
    public void facturar() {
        try {
            int id_cabecera = vista.controlador.agregarFacturaCabecera(this.cabecera);
            
            if (id_cabecera > 0) {
                if (this.detalles.isEmpty()) {
                    vista.message.errorMessage("Error: La factura debe tener al menos un servicio asociado.");
                } else {
                    vista.setCabeceras(vista.controlador.obtenerCabeceras());
                    this.cabecera.setCabId(id_cabecera);
                    boolean agregarDetalles = vista.controlador.agregarFacturaDetalle(detalles);
                    if (agregarDetalles) {
                        vista.message.successMessage("Factura creada correctamente!");
                        this.pdfGenerator.generarPDF();
                        String destino = this.cabecera.getCliente().getPersona().getPerCorreoElectronico();
                        this.envioCorreo.enviarCorreo(destino);
                        vista.setDetalles(vista.controlador.obtenerDetalles());
                        vaciarInfoCliente();
                        limpiarCampos();
                    } else {
                        vista.message.errorMessage("Error al crear la factura.");
                    }
                }
                
            } else {
                vista.message.errorMessage("Error al crear la factura.");
            }
        } catch (NullPointerException e) {
            vista.message.errorMessage("Seleccionar un cliente para la factura.");
        }
        
    }
    
    public void limpiarCampos() {
        vista.getTable_factura_detalle().getItems().clear();
        vista.getTabla_factura_cabecera().getItems().clear();
        vista.getLabel_visor_total().setText("0.00");
        this.detalles = new ArrayList<>();
        this.cabecera = new FacturaCabecera(fechaActual, GlobalValues.userApp);
        this.pdfGenerator = new FacturaPDF(cabecera);
        vista.controlador.actualizarNumeroFactura();
        vista.getFactura_label_nro().setText("Nro. Factura: " + GlobalValues.numeroFactura);
        setConsumidorFinal();
    }
    
    public void llenarInfoCliente(Cliente cliente) {
        vista.getFactura_label_apellido().setText("Apellido: " + cliente.getPersona().getPerApellido());
        vista.getFactura_label_cedula().setText("Cedula: " + cliente.getPersona().getPerCedula());
        vista.getFactura_label_correo().setText("Correo: " + cliente.getPersona().getPerCorreoElectronico());
        vista.getFactura_label_direccion().setText("Direccion: " + cliente.getPersona().getPerDireccion());
        vista.getFactura_label_nombre().setText("Nombre: " + cliente.getPersona().getPerNombre());
        vista.getFactura_label_telefono().setText("Telefono: " + cliente.getPersona().getPerTelefono());
    }
    
    public void vaciarInfoCliente() {
        vista.getFactura_label_apellido().setText("Apellido: ");
        vista.getFactura_label_cedula().setText("Cedula: ");
        vista.getFactura_label_correo().setText("Correo: ");
        vista.getFactura_label_direccion().setText("Direccion: ");
        vista.getFactura_label_nombre().setText("Nombre: ");
        vista.getFactura_label_telefono().setText("Telefono: ");
    }
    
    public void quitarServicio() {
        if (!vista.getTable_factura_detalle().getItems().isEmpty()) {
            FacturaDetalle detalleEliminar = vista.getTable_factura_detalle().getItems().get(vista.indiceEliminarDetalle);
            vista.getTable_factura_detalle().getItems().remove(detalleEliminar);
            this.detalles.remove(detalleEliminar);
            this.pdfGenerator.detalles.remove(detalleEliminar);
            this.cabecera.actualizarData(detalles);
            vista.getLabel_visor_total().setText(String.valueOf(this.cabecera.getCabTotal()));
            vista.tableWorker.setDataFacturaCabecera(this.cabecera);
        } else{
            vista.message.errorMessage("No hay detalles factura para eliminar.");
        }
        
    }
    
    public void setConsumidorFinal(){
        this.cliente = vista.controlador.empj.encontrarConsumidorFinal(vista.controlador.obtenerClientes(vista.controlador.obtenerPersonas()));
        llenarInfoCliente(cliente);
        cabecera.setCliente(cliente);
    }
    
}
