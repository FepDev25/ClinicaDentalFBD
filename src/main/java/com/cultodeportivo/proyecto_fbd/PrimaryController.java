package com.cultodeportivo.proyecto_fbd;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    private List<String> tiposEmpleados = Arrays.asList("Administrativo", "General");
    private AlertMessage message = new AlertMessage();

    @FXML
    private Label bienvenida;

    @FXML
    private Label nombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_usuario.setItems(FXCollections.observableArrayList(tiposEmpleados));
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
                    switchToSecondary();
                } else{
                    message.errorMessage("Informacion Incorrecta.");
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
                password_ingresado = password.getText();
                datos.add(password_ingresado);
            } 
        }

        return datos;
    }

    private boolean validar_combo() {
        try {
            if (combo_usuario.getValue().equals("Administrativo")) {
                message.successMessage("Entrando como Admin...");
            } else if (combo_usuario.getValue().equals("General")) {
                message.successMessage("Entrando como General...");              
            }
            return true;
        } catch (NullPointerException e) {
            return false;  
        }
    }
   
    // A nivel de base de datos
    private boolean validar_informacion (ArrayList<String> datos){
        System.out.println(datos.get(0));
        System.out.println(datos.get(1));

        if (datos.get(0).equals("Felipe") && datos.get(1).equals("12345")) {
            return true;
        }
        return false;
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}