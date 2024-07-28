package com.cultodeportivo.proyecto_fbd;

import com.cultodeportivo.Control.Control;
import com.cultodeportivo.Modelos.Cita;
import com.cultodeportivo.Modelos.Cliente;
import com.cultodeportivo.Modelos.Empleado;
import com.cultodeportivo.Modelos.FacturaCabecera;
import com.cultodeportivo.Modelos.FacturaDetalle;
import com.cultodeportivo.Modelos.Permiso;
import com.cultodeportivo.Modelos.Persona;
import com.cultodeportivo.Modelos.Servicio;
import com.cultodeportivo.Modelos.Tipo;
import com.cultodeportivo.Modelos.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SecondaryController implements Initializable {

    private ArrayList<Persona> personas;
    private ArrayList<Permiso> permisos;
    private ArrayList<Tipo> tipos;
    private ArrayList<Empleado> empleados;
    private ArrayList<Cliente> clientes;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cita> citas;
    private ArrayList<FacturaCabecera> cabeceras;
    private ArrayList<FacturaDetalle> detalles;
    private ArrayList<Servicio> servicios;
    private ButtonWorker btnWorker;
    private ButtonCitas btnCitas;
    private ButtonFacturacion btnFact;

    public Control controlador;
    public AlertMessage message;

    public int indiceClientes;
    public int indiceServicios;
    public int indiceEmpleados;
    public int indiceFacturaDetlles;
    
    public int indiceDoctoresCitas;
    public int indiceClientesCitas;
    
    public int indiceCitaEliminada;
    
    public int indiceServicioFacturacion;
    public int indiceClienteFacturacion;
    
    public TableWorker tableWorker;

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public ArrayList<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList<Permiso> permisos) {
        this.permisos = permisos;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }

    public ArrayList<FacturaCabecera> getCabeceras() {
        return cabeceras;
    }

    public void setCabeceras(ArrayList<FacturaCabecera> cabeceras) {
        this.cabeceras = cabeceras;
    }

    public ArrayList<FacturaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<FacturaDetalle> detalles) {
        this.detalles = detalles;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public ArrayList<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(ArrayList<Tipo> tipos) {
        this.tipos = tipos;
    }
    
    List<String> yes_no = Arrays.asList("Si", "No");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = new Control(this);
        tableWorker = new TableWorker(this);
        btnWorker = new ButtonWorker(this);
        btnCitas = new ButtonCitas(this);
        btnFact = new ButtonFacturacion(this);
        accionBotones();
        validarPermiso();
        label_usuario.setText("Usuario: " + GlobalValues.userApp.getUsrNombre());
        controlador.cargarListasController2();
        tableWorker.cargarValuesTablas();
        tableWorker.setDataTablas();
        btnWorker.asignarAcciones();
        btnCitas.asignarAcciones();
        btnFact.asignarAcciones();
        message = new AlertMessage();

        this.tabla_cliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceClientes = this.tabla_cliente.getItems().indexOf(newSelection);
            }
        });
        
        this.tabla_servicios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceServicios = this.tabla_servicios.getItems().indexOf(newSelection);
            }
        });
        
        this.tabla_empleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceEmpleados = this.tabla_empleados.getItems().indexOf(newSelection);
            }
        });
        
        this.tabla_seleccionar_doctor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceDoctoresCitas = this.tabla_seleccionar_doctor.getItems().indexOf(newSelection);
            }
        });
        
        this.tabla_seleccionar_cliente_citas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceClientesCitas = this.tabla_seleccionar_cliente_citas.getItems().indexOf(newSelection);
            }
        });
        
        this.tabla_cancelar_citas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceCitaEliminada = this.tabla_cancelar_citas.getItems().indexOf(newSelection);
            }
        });
        
        this.table_servicios_facturas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceServicioFacturacion = this.table_servicios_facturas.getItems().indexOf(newSelection);
            }
        });
        
        this.tabla_seleccionar_cliente_facturas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                indiceClienteFacturacion = this.tabla_seleccionar_cliente_facturas.getItems().indexOf(newSelection);
            }
        });
        
        
        
        llenarCombos();
    }

    @FXML
    private AnchorPane alterar_clientes;

    @FXML
    private AnchorPane alterar_clientes_2;

    @FXML
    private AnchorPane alterar_empleados;

    @FXML
    private AnchorPane alterar_empleados_2;

    @FXML
    private AnchorPane alterar_servicios;

    @FXML
    private Button boton_agendar_cita;

    @FXML
    private Button boton_agregar_cliente;

    @FXML
    private Button boton_agregar_empleado;

    @FXML
    private Button boton_agregar_servicio;

    @FXML
    private Button boton_buscar_cliente;

    @FXML
    private Button boton_buscar_servicio;

    @FXML
    private Button boton_cancelar_cita;

    @FXML
    private Button boton_citas;

    @FXML
    private Button boton_clientes;

    @FXML
    private Button boton_eliminar_cliente;

    @FXML
    private Button boton_eliminar_empleado;

    @FXML
    private Button boton_eliminar_servicios;

    @FXML
    private Button boton_empleados;

    @FXML
    private Button boton_facturacion;

    @FXML
    private Button boton_facturar;

    @FXML
    private Button boton_guardar_clientes;

    @FXML
    private Button boton_guardar_empleado;

    @FXML
    private Button boton_guardar_servicio;

    @FXML
    private Button boton_limpiar_campos_cliente;

    @FXML
    private Button boton_limpiar_campos_empleado;

    @FXML
    private Button boton_limpiar_campos_servicios;

    @FXML
    private Button boton_modificar_cliente;

    @FXML
    private Button boton_modificar_empleado;

    @FXML
    private Button boton_modificar_servicio;

    @FXML
    private Button boton_perfil;

    @FXML
    private Button boton_reservar_cita;

    @FXML
    private Button boton_salir;

    @FXML
    private Button boton_servicios;

    @FXML
    private Button button_seleccionar_cliente_citas;

    @FXML
    private Button button_seleccionar_odontologo_citas;

    @FXML
    private TextField cliente_apellidos;

    @FXML
    private TextField cliente_cedula;

    @FXML
    private TextField cliente_correo;

    @FXML
    private TextArea cliente_direccion;

    @FXML
    private TextField cliente_nombre;

    @FXML
    private TextField cliente_telefono;

    @FXML
    private ComboBox<String> combo_empleado_permiso;

    @FXML
    private ComboBox<String> combo_empleado_tipo;

    @FXML
    private ComboBox<String> combo_servicios_iva;

    @FXML
    private TextField empleado_apellidos;

    @FXML
    private TextField empleado_cedula;

    @FXML
    private PasswordField empleado_contrasena;

    @FXML
    private TextField empleado_correo;

    @FXML
    private TextArea empleado_direccion;

    @FXML
    private TextField empleado_nombre;

    @FXML
    private TextField empleado_telefono;

    @FXML
    private TextField empleado_usuario;

    @FXML
    private Label factura_label_apellido;

    @FXML
    private Label factura_label_cedula;

    @FXML
    private Label factura_label_correo;

    @FXML
    private Label factura_label_direccion;

    @FXML
    private Label factura_label_fecha;

    @FXML
    private Label factura_label_nombre;

    @FXML
    private Label factura_label_nro;

    @FXML
    private Label factura_label_telefono;

    public Label getFactura_label_apellido() {
        return factura_label_apellido;
    }

    public Label getFactura_label_cedula() {
        return factura_label_cedula;
    }

    public Label getFactura_label_correo() {
        return factura_label_correo;
    }

    public Label getFactura_label_direccion() {
        return factura_label_direccion;
    }

    public Label getFactura_label_fecha() {
        return factura_label_fecha;
    }

    public Label getFactura_label_nombre() {
        return factura_label_nombre;
    }

    public Label getFactura_label_nro() {
        return factura_label_nro;
    }

    public Label getFactura_label_telefono() {
        return factura_label_telefono;
    }
    
    

    @FXML
    private AnchorPane frame_barra_lateral;

    @FXML
    private AnchorPane frame_barra_superior;

    @FXML
    private AnchorPane frame_cancelar_cita;

    @FXML
    private AnchorPane frame_citas;

    @FXML
    private AnchorPane frame_clientes;

    @FXML
    private AnchorPane frame_empleados;

    @FXML
    private AnchorPane frame_facturacion;

    @FXML
    private AnchorPane frame_perfil;

    @FXML
    private AnchorPane frame_reservar_citas;

    @FXML
    private AnchorPane frame_servicios;

    @FXML
    private TextField label_seleccionar_cantidad;

    @FXML
    private Label label_usuario;

    @FXML
    private Label label_usuario1;

    @FXML
    private Label label_usuario11;

    @FXML
    private Label label_visor_total;

    @FXML
    private DatePicker reserva_fecha;

    @FXML
    private TextField reserva_label_hora;

    @FXML
    private TextField reserva_label_minutos;

    @FXML
    private TextField servicios_nombre;

    @FXML
    private TextField servicios_precio;

    @FXML
    private TextField text_seleccionar_cliente_citas;

    @FXML
    private TextField text_seleccionar_odontologo_citas;


    @FXML
    private TableColumn<Cliente, String> tab_cli_apellidos;

    @FXML
    private TableColumn<Cliente, String> tab_cli_ccorreo;

    @FXML
    private TableColumn<Cliente, String> tab_cli_cedula;

    @FXML
    private TableColumn<Cliente, String> tab_cli_direccion;

    @FXML
    private TableColumn<Cliente, Character> tab_cli_estado;

    @FXML
    private TableColumn<Cliente, Integer> tab_cli_id;

    @FXML
    private TableColumn<Cliente, String> tab_cli_nombre;

    @FXML
    private TableColumn<Cliente, String> tab_cli_telefono;

    @FXML
    private TableColumn<Empleado, String> tab_emp_apellido;

    @FXML
    private TableColumn<Empleado, String> tab_emp_cedula;

    @FXML
    private TableColumn<Empleado, String> tab_emp_correo;

    @FXML
    private TableColumn<Empleado, String> tab_emp_direccion;

    @FXML
    private TableColumn<Empleado, Integer> tab_emp_id;

    @FXML
    private TableColumn<Empleado, String> tab_emp_nombre;

    @FXML
    private TableColumn<Empleado, String> tab_emp_telefono;

    @FXML
    private TableColumn<Empleado, String> tab_emp_tipo;

    @FXML
    private TableColumn<Servicio, Character> tab_ser_estado;

    @FXML
    private TableColumn<Servicio, Integer> tab_ser_id;

    @FXML
    private TableColumn<Servicio, Double> tab_ser_precio;

    @FXML
    private TableColumn<Servicio, Character> tab_ser_iva;

    @FXML
    private TableColumn<Servicio, String> tab_ser_nombre;

    @FXML
    private TableView<Cliente> tabla_cliente;

    @FXML
    private TableView<Empleado> tabla_empleados;

    @FXML
    private TableView<Servicio> tabla_servicios;

    // Tabla de citas proximas 
    @FXML
    private TableView<Cita> table_citas_recientes;
    
    @FXML
    private TableColumn<Cita, String> tab_cit_cliente;

    @FXML
    private TableColumn<Cita, String> tab_cit_hora;

    @FXML
    private TableColumn<Cita, String> tab_cit_odontologo;

    public TableView<Cita> getTable_citas_recientes() {
        return table_citas_recientes;
    }

    public TableColumn<Cita, String> getTab_cit_cliente() {
        return tab_cit_cliente;
    }

    public TableColumn<Cita, String> getTab_cit_hora() {
        return tab_cit_hora;
    }

    public TableColumn<Cita, String> getTab_cit_odontologo() {
        return tab_cit_odontologo;
    }
    
    
    
    // Detalles factura en menu facturacion 

    @FXML
    private TableView<FacturaDetalle> table_factura_detalle;

    @FXML
    private TableColumn<FacturaDetalle, Integer> tb_fac_det_cantidad;

    @FXML
    private TableColumn<FacturaDetalle, Double> tb_fac_det_iva;

    @FXML
    private TableColumn<FacturaDetalle, String> tb_fac_det_nombre;

    @FXML
    private TableColumn<FacturaDetalle, Double> tb_fac_det_subtotal;
    
    @FXML
    private TableColumn<FacturaDetalle, Double> tb_fac_unitario;
    
    @FXML
    private TableColumn<FacturaDetalle, Double> tb_fac_det_total;

    public TableView<FacturaDetalle> getTable_factura_detalle() {
        return table_factura_detalle;
    }

    public TableColumn<FacturaDetalle, Integer> getTb_fac_det_cantidad() {
        return tb_fac_det_cantidad;
    }

    public TableColumn<FacturaDetalle, Double> getTb_fac_det_iva() {
        return tb_fac_det_iva;
    }

    public TableColumn<FacturaDetalle, String> getTb_fac_det_nombre() {
        return tb_fac_det_nombre;
    }

    public TableColumn<FacturaDetalle, Double> getTb_fac_det_subtotal() {
        return tb_fac_det_subtotal;
    }

    public TableColumn<FacturaDetalle, Double> getTb_fac_det_total() {
        return tb_fac_det_total;
    }

    public TableColumn<FacturaDetalle, Double> getTb_fac_unitario() {
        return tb_fac_unitario;
    }
    
    
    
    

    // Factura cabecera en menu facturacion
    @FXML
    private TableView<FacturaCabecera> tabla_factura_cabecera;

    @FXML
    private TableColumn<FacturaCabecera, Double> tabla_factura_cabecera_iva;

    @FXML
    private TableColumn<FacturaCabecera, Double> tabla_factura_cabecera_subtotal;

    @FXML
    private TableColumn<FacturaCabecera, Double> tabla_factura_cabecera_total;

    public TableView<FacturaCabecera> getTabla_factura_cabecera() {
        return tabla_factura_cabecera;
    }

    public TableColumn<FacturaCabecera, Double> getTabla_factura_cabecera_iva() {
        return tabla_factura_cabecera_iva;
    }

    public TableColumn<FacturaCabecera, Double> getTabla_factura_cabecera_subtotal() {
        return tabla_factura_cabecera_subtotal;
    }

    public TableColumn<FacturaCabecera, Double> getTabla_factura_cabecera_total() {
        return tabla_factura_cabecera_total;
    }

    public Label getLabel_visor_total() {
        return label_visor_total;
    }
    
    // Seleccionar cliente facturacion
    
    @FXML
    private AnchorPane seleccionar_cliente_facturas;
    @FXML
    private TextField buscar_cliente_facturas;
    @FXML
    private TableView<Cliente> tabla_seleccionar_cliente_facturas;
    @FXML
    private Button boton_cancelar_seleccion_cli_fac;
    @FXML
    private Button boton_aceptar_seleccion_cli_fac;
    @FXML
    private TableColumn<Cliente, String> tb_selec_cli_nombre;
    @FXML
    private TableColumn<Cliente, String> tb_selec_cli_apellido;
    @FXML
    private TableColumn<Cliente, String> tb_selec_cli_cedula;
    public AnchorPane getSeleccionar_cliente_facturas() {
        return seleccionar_cliente_facturas;
    }
    public TextField getBuscar_cliente_facturas() {
        return buscar_cliente_facturas;
    }
    public TableView<Cliente> getTabla_seleccionar_cliente_facturas() {
        return tabla_seleccionar_cliente_facturas;
    }
    public Button getBoton_cancelar_seleccion_cli_fac() {
        return boton_cancelar_seleccion_cli_fac;
    }
    public Button getBoton_aceptar_seleccion_cli_fac() {
        return boton_aceptar_seleccion_cli_fac;
    }
    public TableColumn<Cliente, String> getTb_selec_cli_nombre() {
        return tb_selec_cli_nombre;
    }
    public TableColumn<Cliente, String> getTb_selec_cli_apellido() {
        return tb_selec_cli_apellido;
    }
    public TableColumn<Cliente, String> getTb_selec_cli_cedula() {
        return tb_selec_cli_cedula;
    }
    
    
    // Seleccionar doctor citas
    @FXML
    private TextField buscador_doctor;
    
    @FXML
    private AnchorPane seleccionar_doctor;
    
    @FXML
    private TableView<Empleado> tabla_seleccionar_doctor;
     
    @FXML
    private Button boton_cancelar_seleccion_doctor;
     
    @FXML
    private Button boton_aceptar_seleccion_doctor;
    
    @FXML
    private TableColumn<Empleado, String> tb_selec_doc_nombre;
    
    @FXML
    private TableColumn<Empleado, String> tb_selec_doc_apellido;

    public AnchorPane getSeleccionar_doctor() {
        return seleccionar_doctor;
    }

    public TableView<Empleado> getTabla_seleccionar_doctor() {
        return tabla_seleccionar_doctor;
    }

    public Button getBoton_cancelar_seleccion_doctor() {
        return boton_cancelar_seleccion_doctor;
    }

    public Button getBoton_aceptar_seleccion_doctor() {
        return boton_aceptar_seleccion_doctor;
    }

    public TableColumn<Empleado, String> getTb_selec_doc_nombre() {
        return tb_selec_doc_nombre;
    }

    public TableColumn<Empleado, String> getTb_selec_doc_apellido() {
        return tb_selec_doc_apellido;
    }

    public TextField getText_seleccionar_odontologo_citas() {
        return text_seleccionar_odontologo_citas;
    }
    
    public TextField getBuscador_doctor() {
        return buscador_doctor;
    }
    
    // Seleccionar Cliente citas
    @FXML
    private AnchorPane seleccionar_cliente_citas;
    
    @FXML
    private TextField buscador_cliente_citas;
    
    @FXML
    private TableView<Cliente> tabla_seleccionar_cliente_citas;
     
    @FXML
    private Button boton_cancelar_seleccion_cliente_citas;
     
    @FXML
    private Button boton_aceptar_seleccion_cliente_citas;
    
    @FXML
    private TableColumn<Cliente, String> tb_selec_cli_cit_nombre;
    
    @FXML
    private TableColumn<Cliente, String> tb_selec_cli_cit_apellido;

    public AnchorPane getSeleccionar_cliente_citas() {
        return seleccionar_cliente_citas;
    }

    public TextField getBuscador_cliente_citas() {
        return buscador_cliente_citas;
    }

    public TableView<Cliente> getTabla_seleccionar_cliente_citas() {
        return tabla_seleccionar_cliente_citas;
    }

    public Button getBoton_cancelar_seleccion_cliente_citas() {
        return boton_cancelar_seleccion_cliente_citas;
    }

    public Button getBoton_aceptar_seleccion_cliente_citas() {
        return boton_aceptar_seleccion_cliente_citas;
    }

    public TableColumn<Cliente, String> getTb_selec_cli_cit_nombre() {
        return tb_selec_cli_cit_nombre;
    }

    public TableColumn<Cliente, String> getTb_selec_cli_cit_apellido() {
        return tb_selec_cli_cit_apellido;
    }

    public TextField getText_seleccionar_cliente_citas() {
        return text_seleccionar_cliente_citas;
    }

    public DatePicker getReserva_fecha() {
        return reserva_fecha;
    }

    public TextField getReserva_label_hora() {
        return reserva_label_hora;
    }

    public TextField getReserva_label_minutos() {
        return reserva_label_minutos;
    }
     
    // Cancelar Citas
    
    @FXML
    private TableView<Cita> tabla_cancelar_citas;
     
    @FXML
    private Button boton_cancelar_cita_accion;
    
    @FXML
    private TableColumn<Cita, Integer> tb_cancelar_cita_id;
    
    @FXML
    private TableColumn<Cita, String> tb_cancelar_cita_fecha;
    
    @FXML
    private TableColumn<Cita, String> tb_cancelar_cita_odontologo;
    
    @FXML
    private TableColumn<Cita, String> tb_cancelar_cita_cliente;
    
    @FXML
    private TableColumn<Cita, String> tb_cancelar_cita_estado;

    public TableView<Cita> getTabla_cancelar_citas() {
        return tabla_cancelar_citas;
    }

    public Button getBoton_cancelar_cita_accion() {
        return boton_cancelar_cita_accion;
    }

    public TableColumn<Cita, Integer> getTb_cancelar_cita_id() {
        return tb_cancelar_cita_id;
    }

    public TableColumn<Cita, String> getTb_cancelar_cita_fecha() {
        return tb_cancelar_cita_fecha;
    }

    public TableColumn<Cita, String> getTb_cancelar_cita_odontologo() {
        return tb_cancelar_cita_odontologo;
    }

    public TableColumn<Cita, String> getTb_cancelar_cita_cliente() {
        return tb_cancelar_cita_cliente;
    }

    public TableColumn<Cita, String> getTb_cancelar_cita_estado() {
        return tb_cancelar_cita_estado;
    }
    
    
    
     @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary", 800, 600);
    }

    public void accionBotones() {
        boton_facturacion.setOnAction(e -> cambiarVistaBotones("Facturacion"));
        boton_citas.setOnAction(e -> cambiarVistaBotones("Citas"));
        boton_clientes.setOnAction(e -> cambiarVistaBotones("Clientes"));
        boton_empleados.setOnAction(e -> cambiarVistaBotones("Empleados"));
        boton_perfil.setOnAction(e -> cambiarVistaBotones("Perfil"));
        boton_servicios.setOnAction(e -> cambiarVistaBotones("Servicios"));
        
        boton_reservar_cita.setOnAction(e -> {
            frame_cancelar_cita.setVisible(false);
            frame_reservar_citas.setVisible(true);
        });
        
        boton_cancelar_cita.setOnAction(e -> {
            frame_reservar_citas.setVisible(false);
            frame_cancelar_cita.setVisible(true);
        });
    }

    public void cambiarVistaBotones(String clave) {
        List<AnchorPane> frames = Arrays.asList(frame_perfil, frame_citas, frame_clientes, frame_empleados, frame_servicios, frame_facturacion);
        for (AnchorPane frame : frames) {
            if (clave.equals("Facturacion") && (frame == frame_facturacion)) {
                frame.setVisible(true);
            } else if (clave.equals("Citas") && (frame == frame_citas)) {
                frame.setVisible(true);
            } else if (clave.equals("Clientes") && (frame == frame_clientes)) {
                frame.setVisible(true);
            } else if (clave.equals("Empleados") && (frame == frame_empleados)) {
                frame.setVisible(true);
            } else if (clave.equals("Perfil") && (frame == frame_perfil)) {
                frame.setVisible(true);
            } else if (clave.equals("Servicios") && (frame == frame_servicios)) {
                frame.setVisible(true);
            } else {
                frame.setVisible(false);
            }
        }

    }

    public void validarPermiso() {
        if (GlobalValues.userApp.getPermiso().getPrmTipo().equals("General")) {
            alterar_clientes.setDisable(true);
            alterar_clientes_2.setDisable(true);
            alterar_empleados.setDisable(true);
            alterar_empleados_2.setDisable(true);
            alterar_servicios.setDisable(true);
        }
    }

    public TableColumn<Servicio, Character> getTab_ser_estado() {
        return tab_ser_estado;
    }

    public TableColumn<Servicio, Integer> getTab_ser_id() {
        return tab_ser_id;
    }

    public TableColumn<Servicio, Character> getTab_ser_iva() {
        return tab_ser_iva;
    }

    public TableColumn<Servicio, String> getTab_ser_nombre() {
        return tab_ser_nombre;
    }

    public TableColumn<Servicio, Double> getTab_ser_precio() {
        return tab_ser_precio;
    }

    public TableView<Servicio> getTabla_servicios() {
        return tabla_servicios;
    }

    public TableColumn<Cliente, String> getTab_cli_apellidos() {
        return tab_cli_apellidos;
    }

    public TableColumn<Cliente, String> getTab_cli_ccorreo() {
        return tab_cli_ccorreo;
    }

    public TableColumn<Cliente, String> getTab_cli_cedula() {
        return tab_cli_cedula;
    }

    public TableColumn<Cliente, String> getTab_cli_direccion() {
        return tab_cli_direccion;
    }

    public TableColumn<Cliente, Character> getTab_cli_estado() {
        return tab_cli_estado;
    }

    public TableColumn<Cliente, Integer> getTab_cli_id() {
        return tab_cli_id;
    }

    public TableColumn<Cliente, String> getTab_cli_nombre() {
        return tab_cli_nombre;
    }

    public TableColumn<Cliente, String> getTab_cli_telefono() {
        return tab_cli_telefono;
    }

    public TableView<Cliente> getTabla_cliente() {
        return tabla_cliente;
    }

    public TableColumn<Empleado, String> getTab_emp_apellido() {
        return tab_emp_apellido;
    }

    public TableColumn<Empleado, String> getTab_emp_cedula() {
        return tab_emp_cedula;
    }

    public TableColumn<Empleado, String> getTab_emp_correo() {
        return tab_emp_correo;
    }

    public TableColumn<Empleado, String> getTab_emp_direccion() {
        return tab_emp_direccion;
    }

    public TableColumn<Empleado, Integer> getTab_emp_id() {
        return tab_emp_id;
    }

    public TableColumn<Empleado, String> getTab_emp_nombre() {
        return tab_emp_nombre;
    }

    public TableColumn<Empleado, String> getTab_emp_telefono() {
        return tab_emp_telefono;
    }

    public TableColumn<Empleado, String> getTab_emp_tipo() {
        return tab_emp_tipo;
    }

    public TableView<Empleado> getTabla_empleados() {
        return tabla_empleados;
    }

    public Button getBoton_agendar_cita() {
        return boton_agendar_cita;
    }

    public Button getBoton_agregar_cliente() {
        return boton_agregar_cliente;
    }

    public Button getBoton_agregar_empleado() {
        return boton_agregar_empleado;
    }

    public Button getBoton_agregar_servicio() {
        return boton_agregar_servicio;
    }

    public Button getBoton_buscar_cliente() {
        return boton_buscar_cliente;
    }

    public Button getBoton_buscar_servicio() {
        return boton_buscar_servicio;
    }

    public Button getBoton_cancelar_cita() {
        return boton_cancelar_cita;
    }

    public Button getBoton_citas() {
        return boton_citas;
    }

    public Button getBoton_clientes() {
        return boton_clientes;
    }

    public Button getBoton_eliminar_cliente() {
        return boton_eliminar_cliente;
    }

    public Button getBoton_eliminar_empleado() {
        return boton_eliminar_empleado;
    }

    public Button getBoton_eliminar_servicios() {
        return boton_eliminar_servicios;
    }

    public Button getBoton_empleados() {
        return boton_empleados;
    }

    public Button getBoton_facturacion() {
        return boton_facturacion;
    }

    public Button getBoton_facturar() {
        return boton_facturar;
    }

    public Button getBoton_guardar_clientes() {
        return boton_guardar_clientes;
    }

    public Button getBoton_guardar_empleado() {
        return boton_guardar_empleado;
    }

    public Button getBoton_guardar_servicio() {
        return boton_guardar_servicio;
    }

    public Button getBoton_limpiar_campos_cliente() {
        return boton_limpiar_campos_cliente;
    }

    public Button getBoton_limpiar_campos_empleado() {
        return boton_limpiar_campos_empleado;
    }

    public Button getBoton_limpiar_campos_servicios() {
        return boton_limpiar_campos_servicios;
    }

    public Button getBoton_modificar_cliente() {
        return boton_modificar_cliente;
    }

    public Button getBoton_modificar_empleado() {
        return boton_modificar_empleado;
    }

    public Button getBoton_modificar_servicio() {
        return boton_modificar_servicio;
    }

    public Button getBoton_perfil() {
        return boton_perfil;
    }

    public Button getBoton_reservar_cita() {
        return boton_reservar_cita;
    }

    public Button getBoton_salir() {
        return boton_salir;
    }

    public Button getBoton_servicios() {
        return boton_servicios;
    }

    public Button getButton_seleccionar_cliente_citas() {
        return button_seleccionar_cliente_citas;
    }

    public Button getButton_seleccionar_odontologo_citas() {
        return button_seleccionar_odontologo_citas;
    }

    public TextField getCliente_apellidos() {
        return cliente_apellidos;
    }

    public TextField getCliente_cedula() {
        return cliente_cedula;
    }

    public TextField getCliente_correo() {
        return cliente_correo;
    }

    public TextArea getCliente_direccion() {
        return cliente_direccion;
    }

    public TextField getCliente_nombre() {
        return cliente_nombre;
    }

    public TextField getCliente_telefono() {
        return cliente_telefono;
    }
    
    // Para ingresar servicios
    
    public ComboBox<String> getCombo_servicios_iva() {
        return combo_servicios_iva;
    }

    public TextField getServicios_nombre() {
        return servicios_nombre;
    }

    public TextField getServicios_precio() {
        return servicios_precio;
    }
    
    // Empleados

    public ComboBox<String> getCombo_empleado_permiso() {
        return combo_empleado_permiso;
    }

    public ComboBox<String> getCombo_empleado_tipo() {
        return combo_empleado_tipo;
    }

    public TextField getEmpleado_apellidos() {
        return empleado_apellidos;
    }

    public TextField getEmpleado_cedula() {
        return empleado_cedula;
    }

    public PasswordField getEmpleado_contrasena() {
        return empleado_contrasena;
    }

    public TextField getEmpleado_correo() {
        return empleado_correo;
    }

    public TextArea getEmpleado_direccion() {
        return empleado_direccion;
    }

    public TextField getEmpleado_nombre() {
        return empleado_nombre;
    }

    public TextField getEmpleado_telefono() {
        return empleado_telefono;
    }

    public TextField getEmpleado_usuario() {
        return empleado_usuario;
    }
    
    // Seleccionar servicios factura 
    @FXML
    private TableView<Servicio> table_servicios_facturas;
    
    @FXML
    private TableColumn<Servicio, String> table_citas_facturas_nombre;
    
    @FXML
    private TableColumn<Servicio, Double> table_citas_facturas_precio;
    
    @FXML
    private TextField label_buscar_servicio;

    public TableView<Servicio> getTable_servicios_facturas() {
        return table_servicios_facturas;
    }

    public TableColumn<Servicio, String> getTable_citas_facturas_nombre() {
        return table_citas_facturas_nombre;
    }

    public TableColumn<Servicio, Double> getTable_citas_facturas_precio() {
        return table_citas_facturas_precio;
    }

    public TextField getLabel_buscar_servicio() {
        return label_buscar_servicio;
    }
    
    public TextField getLabel_seleccionar_cantidad() {
        return label_seleccionar_cantidad;
    }
    public void llenarCombos(){
        List<String> permisosString = new ArrayList<>();
        for (Permiso permiso : this.getPermisos()) {
            permisosString.add(permiso.getPrmTipo());
        }
        
        List<String> tiposString = new ArrayList<>();
        for (Tipo tipo : this.getTipos()) {
            tiposString.add(tipo.getTipNombre());
        }
        
        this.getCombo_servicios_iva().setItems(FXCollections.observableArrayList(yes_no));
        this.getCombo_empleado_permiso().setItems(FXCollections.observableArrayList(permisosString));
        this.getCombo_empleado_tipo().setItems(FXCollections.observableArrayList(tiposString));
    }
    

}
