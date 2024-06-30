package com.cultodeportivo.proyecto_fbd;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.cultodeportivo.Modelos.*;
import com.cultodeportivo.Control.Control;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable {
    
    private List<String> permisosEmpleados = new ArrayList<>();
    private ArrayList<Persona> personas;
    private ArrayList<Permiso> permisos;
    private ArrayList<Tipo> tipos;
    private ArrayList<Empleado> empleados;
    private ArrayList<Usuario> usuarios;
    
    private Control controlador;
    private AlertMessage message = new AlertMessage();

    @FXML
    private Label bienvenida;

    @FXML
    private Label nombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DropShadow shadow = new DropShadow(40, Color.valueOf("#0058FF"));
        nombre.setEffect(shadow);
        bienvenida.setEffect(shadow);
        controlador = new Control(this);
        
        if (obtenerPermisos()){
            combo_usuario.setItems(FXCollections.observableArrayList(permisosEmpleados));
        }
        
        controlador.cargarListasController1();
    }


    @FXML
    private PasswordField password;

    @FXML
    private TextField usuario;

    @FXML
    private Button boton_iniciar;

    @FXML
    private CheckBox check_registro;

    @FXML
    private TextField ver_password;

    @FXML
    private ComboBox<String> combo_usuario;
    
    

    public void mostrar_password() {
        if (check_registro.isSelected()) {
            password.setVisible(false);
            ver_password.setVisible(true);
            ver_password.setText(password.getText());
        } else {
            password.setText(ver_password.getText());
            password.setVisible(true);
            ver_password.setVisible(false);
        }        
    }

    public void iniciar_sesion() throws IOException  {         
        ArrayList<String> datos = validar_ingresos();
        
        if (datos.size() == 2){
            boolean validar_comboBox = validar_combo();
            if (validar_comboBox){
                if (validar_informacion(datos)) {
                    message.successMessage("Bienvenido " + datos.get(0) + "! Inicio de sesión con exito.");
                    switchToSecondary();
                } else{
                    message.errorMessage("Error al Iniciar sesión: Credenciales Incorrectas.");
                }
                
            } else{
                message.errorMessage("Seleccionar el tipo de Empleado.");
            } 
        } else{
            message.errorMessage("Error al iniciar sesion.");
        }
    }

    private ArrayList<String> validar_ingresos(){
        ArrayList<String> datos = new ArrayList<>();

        String username_ingresado;

        if (usuario.getText().isEmpty()) {
            message.errorMessage("Ingresar una usuario.");
        } else{
            username_ingresado = usuario.getText();
            datos.add(username_ingresado);
        }
       

        String password_ingresado;

        if (password.isVisible()) {
            if (password.getText().isEmpty()){
                message.errorMessage("Ingresar una contrasenia.");
            } else{
                password_ingresado = password.getText();
                datos.add(password_ingresado);
            }
            
        } else {
            if (ver_password.getText().isEmpty()){
                message.errorMessage("Ingresar una contrasenia.");
            } else{
                password_ingresado = ver_password.getText();
                datos.add(password_ingresado);
            } 
        }

        return datos;
    }

    private boolean validar_combo() {
        try {
            if (combo_usuario.getValue().equals("Administrador")) {
                message.successMessage("Entrando con permiso Administrador...");
            } else if (combo_usuario.getValue().equals("General")) {
                message.successMessage("Entrando con permiso General...");              
            }
            return true;
        } catch (NullPointerException e) {
            return false;  
        }
    }
   
    // A nivel de base de datos
    private boolean validar_informacion (ArrayList<String> datos){
        String usuario_ingresado = datos.get(0);
        String password_ingresado = datos.get(1);
        String permiso = combo_usuario.getValue();
        
        for (Usuario user : usuarios){
            String nombre_x = user.getUsrNombre();
            String password_x = user.getUsrContrasenia();
            String permiso_x = user.getPermiso().getPrmTipo();
            
            if (usuario_ingresado.equals(nombre_x)){
                if (password_ingresado.equals(password_x)){
                    if (permiso.equals(permiso_x)){
                        GlobalValues.userApp = user;
                        return true;
                    } else{
                        message.errorMessage("Permiso Incorrecto.");
                    }
                } else{
                    message.errorMessage("Contrasenia Incorrecta.");
                }
            }
        }
        
        return false;
    }
    
    private boolean obtenerPermisos(){
        permisos = controlador.obtenerPermisos();
        permisos.forEach(p -> permisosEmpleados.add(p.getPrmTipo()));
        return !permisosEmpleados.isEmpty();
    }
        
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary",1250, 820);
    }

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

    public ArrayList<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(ArrayList<Tipo> tipos) {
        this.tipos = tipos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    
    
    
}