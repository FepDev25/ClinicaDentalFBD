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
    
    private Control controlador;
    
    private TableWorker tableWorker;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = new Control(this);
        tableWorker = new TableWorker(this);
        accionBotones();
        validarPermiso();
        label_usuario.setText("Usuario: " + GlobalValues.userApp.getUsrNombre());
        controlador.cargarListasController2();
        tableWorker.cargarValuesTablas();
        tableWorker.setDataTablas();
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
    private Button boton_listar_facturas_clientes;

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
    private ComboBox<?> combo_seleccionar_cliente;

    @FXML
    private ComboBox<?> combo_seleccionar_doctor;

    @FXML
    private ComboBox<?> combo_servicios_iva;

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
    
    
    
    
    
    

}
