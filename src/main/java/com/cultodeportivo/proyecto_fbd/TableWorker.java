package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.Cita;
import com.cultodeportivo.Modelos.Cliente;
import com.cultodeportivo.Modelos.Empleado;
import com.cultodeportivo.Modelos.FacturaCabecera;
import com.cultodeportivo.Modelos.FacturaDetalle;
import com.cultodeportivo.Modelos.Servicio;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableWorker {

    SecondaryController vista;

    public TableWorker(SecondaryController vista) {
        this.vista = vista;
    }

    public void cargarValuesTablas() {
        valuesServicios();
        valuesClientes();
        valuesEmpleados();
        valuesEmpleadosCitas();
        valuesClientesCitas();
        valuesCitasProximas();
        valuesCitasCancelar();
        valuesServiciosFactura();
        valuesDetallesFactura();
        valuesCabeceraFactura();
        valuesClientesFacturacion();
    }

    public void setDataTablas() {
        setDataServicios();
        setDataClientes();
        setDataEmpleados();
        setDataEmpleadosCitas();
        setDataClientesCitas();
        setDataProximasCitas();
        setDataCancelarCitas();
    }

    private void valuesServicios() {
        vista.getTab_ser_id().setCellValueFactory(new PropertyValueFactory<>("serId"));
        vista.getTab_ser_nombre().setCellValueFactory(new PropertyValueFactory<>("serNombre"));
        vista.getTab_ser_precio().setCellValueFactory(new PropertyValueFactory<>("serPrecio"));
        vista.getTab_ser_iva().setCellValueFactory(new PropertyValueFactory<>("serIva"));
        vista.getTab_ser_estado().setCellValueFactory(new PropertyValueFactory<>("serEstado"));
    }

    private void valuesClientes() {
        vista.getTab_cli_id().setCellValueFactory(new PropertyValueFactory<>("cliId"));
        vista.getTab_cli_estado().setCellValueFactory(new PropertyValueFactory<>("cliEstado"));
        vista.getTab_cli_cedula().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerCedula()));
        vista.getTab_cli_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerNombre()));
        vista.getTab_cli_apellidos().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerApellido()));
        vista.getTab_cli_direccion().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerDireccion()));
        vista.getTab_cli_telefono().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerTelefono()));
        vista.getTab_cli_ccorreo().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerCorreoElectronico()));
    }

    private void valuesEmpleados() {
        vista.getTab_emp_id().setCellValueFactory(new PropertyValueFactory<>("empId"));
        vista.getTab_emp_tipo().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipo().getTipNombre()));
        vista.getTab_emp_cedula().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerCedula()));
        vista.getTab_emp_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerNombre()));
        vista.getTab_emp_apellido().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerApellido()));
        vista.getTab_emp_direccion().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerDireccion()));
        vista.getTab_emp_telefono().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerTelefono()));
        vista.getTab_emp_correo().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerCorreoElectronico()));
    }

    private void valuesEmpleadosCitas() {
        vista.getTb_selec_doc_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerNombre()));
        vista.getTb_selec_doc_apellido().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerApellido()));
    }

    public void valuesClientesCitas() {
        vista.getTb_selec_cli_cit_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerNombre()));
        vista.getTb_selec_cli_cit_apellido().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerApellido()));
    }

    public void valuesCitasProximas() {
        vista.getTab_cit_cliente().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCliente().nombreCompleto()));
        vista.getTab_cit_odontologo().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmpleado().nombreCompleto()));
        vista.getTab_cit_hora().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().fechaString()));
    }

    public void valuesCitasCancelar() {
        vista.getTb_cancelar_cita_id().setCellValueFactory(new PropertyValueFactory<>("citId"));
        vista.getTb_cancelar_cita_estado().setCellValueFactory(new PropertyValueFactory<>("citEstado"));
        vista.getTb_cancelar_cita_odontologo().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmpleado().nombreCompleto()));
        vista.getTb_cancelar_cita_cliente().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCliente().nombreCompleto()));
        vista.getTb_cancelar_cita_fecha().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().fechaString()));
    }
    
    public void valuesServiciosFactura() {
        vista.getTable_citas_facturas_nombre().setCellValueFactory(new PropertyValueFactory<>("serNombre"));
        vista.getTable_citas_facturas_precio().setCellValueFactory(new PropertyValueFactory<>("serPrecio"));
    }
    
    public void valuesDetallesFactura(){
        vista.getTb_fac_det_cantidad().setCellValueFactory(new PropertyValueFactory<>("detCantidad"));
        vista.getTb_fac_det_iva().setCellValueFactory(new PropertyValueFactory<>("detIva"));
        vista.getTb_fac_det_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getServicio().getSerNombre()));
        vista.getTb_fac_det_subtotal().setCellValueFactory(new PropertyValueFactory<>("detSubtotal"));
        vista.getTb_fac_det_total().setCellValueFactory(new PropertyValueFactory<>("detTotal"));
        vista.getTb_fac_unitario().setCellValueFactory(new PropertyValueFactory<>("detPrecioUnitario"));
    }
    
    public void valuesCabeceraFactura(){
        vista.getTabla_factura_cabecera_iva().setCellValueFactory(new PropertyValueFactory<>("cabTotalIva"));
        vista.getTabla_factura_cabecera_subtotal().setCellValueFactory(new PropertyValueFactory<>("cabSubtotal"));
        vista.getTabla_factura_cabecera_total().setCellValueFactory(new PropertyValueFactory<>("cabTotal"));
    }
    
    private void valuesClientesFacturacion() {
        vista.getTb_selec_cli_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerNombre()));
        vista.getTb_selec_cli_apellido().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerApellido()));
        vista.getTb_selec_cli_cedula().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerCedula()));
    }

    public void setDataServicios() {
        vista.getTabla_servicios().setItems(serviciosObservable());
        
        ObservableList<Servicio> servicioFacsObservable = serviciosObservableFacturar();
        FilteredList<Servicio> listaFiltrada = new FilteredList<>(servicioFacsObservable, p -> true);
        vista.getLabel_buscar_servicio().textProperty().addListener((obs, viejoValor, nuevoValor) -> {
            listaFiltrada.setPredicate(servicio -> {
                if (nuevoValor == null || nuevoValor.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = nuevoValor.toLowerCase();
                if (servicio.getSerNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Servicio> sortedData = new SortedList<>(listaFiltrada);
        vista.getTable_servicios_facturas().setItems(sortedData);
    }
    
    public void setDetalleFacturaDetalles(FacturaDetalle detalle){
        vista.getTable_factura_detalle().getItems().add(detalle);
    }
    
    public void setDataFacturaCabecera(FacturaCabecera cabecera){
        vista.getTabla_factura_cabecera().getItems().clear();
        vista.getTabla_factura_cabecera().getItems().add(cabecera);
    }

    public void setDataClientes() {
        vista.getTabla_cliente().setItems(clientesObservable());
        
        ObservableList<Cliente> clientesObservable = clientesActivosObservable();
        FilteredList<Cliente> listaFiltrada = new FilteredList<>(clientesObservable, p -> true);
        vista.getBuscar_cliente_facturas().textProperty().addListener((obs, viejoValor, nuevoValor) -> {
            listaFiltrada.setPredicate(ciente -> {
                if (nuevoValor == null || nuevoValor.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = nuevoValor.toLowerCase();
                if (ciente.getPersona().getPerNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Cliente> sortedData = new SortedList<>(listaFiltrada);
        vista.getTabla_seleccionar_cliente_facturas().setItems(sortedData);
    }

    public void setDataEmpleados() {
        vista.getTabla_empleados().setItems(empleadosObservable());
    }

    public void setDataEmpleadosCitas() {
        ObservableList<Empleado> empleadosObservable = doctoreesObservable();
        FilteredList<Empleado> listaFiltrada = new FilteredList<>(empleadosObservable, p -> true);
        vista.getBuscador_doctor().textProperty().addListener((obs, viejoValor, nuevoValor) -> {
            listaFiltrada.setPredicate(empleado -> {
                if (nuevoValor == null || nuevoValor.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = nuevoValor.toLowerCase();
                if (empleado.getPersona().getPerNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Empleado> sortedData = new SortedList<>(listaFiltrada);
        vista.getTabla_seleccionar_doctor().setItems(sortedData);
    }

    public void setDataClientesCitas() {
        ObservableList<Cliente> clientesObservable = clientesActivosObservable();
        FilteredList<Cliente> listaFiltrada = new FilteredList<>(clientesObservable, p -> true);
        vista.getBuscador_cliente_citas().textProperty().addListener((obs, viejoValor, nuevoValor) -> {
            listaFiltrada.setPredicate(ciente -> {
                if (nuevoValor == null || nuevoValor.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = nuevoValor.toLowerCase();
                if (ciente.getPersona().getPerNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Cliente> sortedData = new SortedList<>(listaFiltrada);
        vista.getTabla_seleccionar_cliente_citas().setItems(sortedData);
    }

    public void setDataProximasCitas() {
        vista.getTable_citas_recientes().setItems(citasObservable());
    }
    
    public void setDataCancelarCitas() {
        vista.getTabla_cancelar_citas().setItems(citasCancelarObservable());
    }

    public ObservableList<Cita> citasObservable() {
        ObservableList<Cita> citasObservable = FXCollections.observableArrayList();
        if (!vista.getCitas().isEmpty()) {
            for (Cita cita : vista.getCitas()) {
                if (cita.getCitFecha().isAfter(LocalDateTime.now()) && (cita.getCitEstado() == 'A')) {
                    citasObservable.add(cita);
                }
            }
        }
        return citasObservable;
    }
    
    public ObservableList<Cita> citasCancelarObservable() {
        ObservableList<Cita> citasObservable = FXCollections.observableArrayList();
        if (!vista.getCitas().isEmpty()) {
            for (Cita cita : vista.getCitas()) {
                citasObservable.add(cita);
            }
        }
        return citasObservable;
    }

    public ObservableList<Servicio> serviciosObservable() {
        ObservableList<Servicio> serviciosObservable = FXCollections.observableArrayList();
        if (!vista.getServicios().isEmpty()) {
            for (Servicio servicio : vista.getServicios()) {
                serviciosObservable.add(servicio);
            }
        }
        return serviciosObservable;
    }
    
    public ObservableList<Servicio> serviciosObservableFacturar() {
        ObservableList<Servicio> serviciosObservable = FXCollections.observableArrayList();
        if (!vista.getServicios().isEmpty()) {
            for (Servicio servicio : vista.getServicios()) {
                if (servicio.getSerEstado() == 'A'){
                    serviciosObservable.add(servicio);
                }
            }
        }
        return serviciosObservable;
    }

    public ObservableList<Cliente> clientesObservable() {
        ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList();
        if (!vista.getClientes().isEmpty()) {
            for (Cliente cliente : vista.getClientes()) {
                clientesObservable.add(cliente);
            }
        }
        return clientesObservable;
    }

    public ObservableList<Cliente> clientesActivosObservable() {
        ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList();
        if (!vista.getClientes().isEmpty()) {
            for (Cliente cliente : vista.getClientes()) {
                if (cliente.getCliEstado() == 'A') {
                    clientesObservable.add(cliente);
                }
            }
        }
        return clientesObservable;
    }

    public ObservableList<Empleado> empleadosObservable() {
        ObservableList<Empleado> empleadosObservable = FXCollections.observableArrayList();
        if (!vista.getEmpleados().isEmpty()) {
            for (Empleado empleado : vista.getEmpleados()) {
                empleadosObservable.add(empleado);
            }
        }
        return empleadosObservable;
    }

    public ObservableList<Empleado> doctoreesObservable() {
        ObservableList<Empleado> empleadosObservable = FXCollections.observableArrayList();
        if (!vista.getEmpleados().isEmpty()) {
            for (Empleado empleado : vista.getEmpleados()) {
                if (empleado.getTipo().getTipNombre().equals("Odontologo")) {
                    empleadosObservable.add(empleado);
                }
            }
        }
        return empleadosObservable;
    }

}
