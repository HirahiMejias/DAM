package org.example.prototipojavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OpcionesController {
    @FXML
    private Button guardarButton;

    @FXML
    public void botonAccederInicio() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) guardarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            Main.showAlert("Ventana Emergente", "Opciones", "Cambios aplicados correctamente");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Button botonCerrar;

    @FXML
    public void cerrarSesion() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) botonCerrar.getScene().getWindow();
            stage.setScene(new Scene(root));
            Main.showAlert("Ventana Emergente", "Login", "Sesión cerrada correctamente");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
