package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Modelos.Cliente;
import com.cultodeportivo.Modelos.Empleado;
import com.cultodeportivo.Modelos.Servicio;
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
    }

    public void setDataTablas() {
        setDataServicios();
        setDataClientes();
        setDataEmpleados();
        setDataEmpleadosCitas();
        setDataClientesCitas();
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
    
    private void valuesEmpleadosCitas(){
        vista.getTb_selec_doc_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerNombre()));
        vista.getTb_selec_doc_apellido().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerApellido()));
    }
    
    public void valuesClientesCitas(){
        vista.getTb_selec_cli_cit_nombre().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerNombre()));
        vista.getTb_selec_cli_cit_apellido().setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPersona().getPerApellido()));
    }

    public void setDataServicios() {
        vista.getTabla_servicios().setItems(serviciosObservable());
    }
    
    public void setDataClientes() {
        vista.getTabla_cliente().setItems(clientesObservable());
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
    
    public void setDataClientesCitas(){
        ObservableList<Cliente> clientesObservable = clientesObservable();
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

    public ObservableList<Servicio> serviciosObservable() {
        ObservableList<Servicio> serviciosObservable = FXCollections.observableArrayList();
        if (!vista.getServicios().isEmpty()) {
            for (Servicio servicio : vista.getServicios()) {
                serviciosObservable.add(servicio);
            }
        }
        System.out.println(serviciosObservable);
        return serviciosObservable;
    }
    
    public ObservableList<Cliente> clientesObservable() {
        ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList();
        if (!vista.getClientes().isEmpty()) {
            for (Cliente cliente : vista.getClientes()) {
                clientesObservable.add(cliente);
            }
        }
        System.out.println(clientesObservable);
        return clientesObservable;
    }
    
    public ObservableList<Empleado> empleadosObservable() {
        ObservableList<Empleado> empleadosObservable = FXCollections.observableArrayList();
        if (!vista.getEmpleados().isEmpty()) {
            for (Empleado empleado : vista.getEmpleados()) {
                empleadosObservable.add(empleado);
            }
        }
        System.out.println(empleadosObservable);
        return empleadosObservable;
    }
    
    public ObservableList<Empleado> doctoreesObservable() {
        ObservableList<Empleado> empleadosObservable = FXCollections.observableArrayList();
        if (!vista.getEmpleados().isEmpty()) {
            for (Empleado empleado : vista.getEmpleados()) {
                if (empleado.getTipo().getTipNombre().equals("Odontologo")){
                    empleadosObservable.add(empleado);
                }
            }
        }
        System.out.println(empleadosObservable);
        return empleadosObservable;
    }

}
