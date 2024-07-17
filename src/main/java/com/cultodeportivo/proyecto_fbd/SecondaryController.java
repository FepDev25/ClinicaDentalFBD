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

    public Control controlador;
    public AlertMessage message;

    public int indiceClientes;
    public int indiceServicios;
    public int indiceEmpleados;
    public int indiceFacturaDetlles;

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
        accionBotones();
        validarPermiso();
        label_usuario.setText("Usuario: " + GlobalValues.userApp.getUsrNombre());
        controlador.cargarListasController2();
        tableWorker.cargarValuesTablas();
        tableWorker.setDataTablas();
        btnWorker.asignarAcciones();
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
        
        this.getCombo_servicios_iva().setItems(FXCollections.observableArrayList(yes_no));
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
    private ComboBox<?> combo_empleado_permiso;

    @FXML
    private ComboBox<?> combo_empleado_tipo;

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
    private TextField label_buscar_servicio;

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
    private TableColumn<?, ?> tab_cit_cliente;

    @FXML
    private TableColumn<?, ?> tab_cit_hora;

    @FXML
    private TableColumn<?, ?> tab_cit_odontologo;

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

    // Detalles factura en menu facturacion 
    @FXML
    private TableView<?> table_citas_recientes;

    @FXML
    private TableView<?> table_factura_detalle;

    @FXML
    private TableColumn<?, ?> tb_fac_det_cantidad;

    @FXML
    private TableColumn<?, ?> tb_fac_det_iva;

    @FXML
    private TableColumn<?, ?> tb_fac_det_nombre;

    @FXML
    private TableColumn<?, ?> tb_fac_det_subtotal;

    @FXML
    private TableColumn<?, ?> tb_fac_det_total;

    // Factura cabecera en menu facturacion
    @FXML
    private TableView<?> tabla_factura_cabecera;

    @FXML
    private TableColumn<?, ?> tabla_factura_cabecera_iva;

    @FXML
    private TableColumn<?, ?> tabla_factura_cabecera_subtotal;

    @FXML
    private TableColumn<?, ?> tabla_factura_cabecera_total;

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

}
