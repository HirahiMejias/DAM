package org.example.prototipojavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button button;

    @FXML
    public void botonAccederInicio() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField contraseñaField;


    @FXML
    protected void InicioSesion() {
        if (usuarioField.getText().isEmpty() || contraseñaField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe rellenar ambos campos: Usuario y Contraseña.");
            alert.showAndWait();
        } else {
            botonAccederInicio();
        }
    }


    @FXML
    private Button telefonoButton;

    @FXML
    public void botonTelefono() {
        Main.showAlert("Ventana Emergente", "Información de Teléfono", "Contacta con nosotros en el 922 22 22 22");
    }

    @FXML
    private Button altavozButton;

    @FXML
    public void botonAltavoz() {
        Main.showAlert("Ventana Emergente", "Activar Altavoz", "Se ha activado el altavoz");
    }
}