package com.cultodeportivo.proyecto_fbd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;

public class PrimaryController implements Initializable {

    @FXML
    private Label bienvenida;

    @FXML
    private Label nombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
   

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
