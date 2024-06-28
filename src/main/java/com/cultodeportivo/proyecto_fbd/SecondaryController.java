package com.cultodeportivo.proyecto_fbd;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SecondaryController implements Initializable{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accionBotones();
    }
    
    @FXML
    private Button boton_citas;

    @FXML
    private Button boton_clientes;

    @FXML
    private Button boton_empleados;

    @FXML
    private Button boton_facturacion;

    @FXML
    private Button boton_perfil;

    @FXML
    private Button boton_servicios;

    @FXML
    private AnchorPane frame_barra_lateral;

    @FXML
    private AnchorPane frame_barra_superior;

    @FXML
    private AnchorPane frame_citas;

    @FXML
    private AnchorPane frame_clientes;

    @FXML
    private AnchorPane frame_empleados;

    @FXML
    private AnchorPane frame_perfil;

    @FXML
    private AnchorPane frame_servicios;
    
    @FXML
    private AnchorPane frame_facturacion;

    @FXML
    private Label label_usuario;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary",800,600);
    }
    
    public void accionBotones(){
        boton_facturacion.setOnAction(e -> cambiarVistaBotones("Facturacion"));
        boton_citas.setOnAction(e -> cambiarVistaBotones("Citas"));
        boton_clientes.setOnAction(e -> cambiarVistaBotones("Clientes"));
        boton_empleados.setOnAction(e -> cambiarVistaBotones("Empleados"));
        boton_perfil.setOnAction(e -> cambiarVistaBotones("Perfil"));
        boton_servicios.setOnAction(e -> cambiarVistaBotones("Servicios"));
    }
    
    public void cambiarVistaBotones(String clave){
        List<AnchorPane> frames = Arrays.asList(frame_perfil, frame_citas, frame_clientes, frame_empleados, frame_servicios, frame_facturacion);
        for (AnchorPane frame : frames){
            if (clave.equals("Facturacion") && (frame == frame_facturacion)){
                frame.setVisible(true);
            } else if (clave.equals("Citas") && (frame == frame_citas)){
                frame.setVisible(true);
            } else if (clave.equals("Clientes") && (frame == frame_clientes)){
                frame.setVisible(true);
            } else if (clave.equals("Empleados") && (frame == frame_empleados)){
                frame.setVisible(true);
            } else if (clave.equals("Perfil") && (frame == frame_perfil)){
                frame.setVisible(true);
            } else if (clave.equals("Servicios") && (frame == frame_servicios)){
                frame.setVisible(true);
            } else{
                frame.setVisible(false);
            }
        }
       
    }

    
}