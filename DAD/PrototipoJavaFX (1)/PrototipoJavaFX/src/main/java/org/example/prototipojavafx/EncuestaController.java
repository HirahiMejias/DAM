package org.example.prototipojavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class EncuestaController {
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

    @FXML
    private RadioButton si;

    @FXML
    public void gracias() {
        Main.showAlert("Ventana Emergente", "Gracias", "Gracias por su valoración");
    }

    @FXML
    private RadioButton no;

    @FXML
    public void losentimos() {
        Main.showAlert("Ventana Emergente", "Lo Sentimos", "Sentimos que no se sienta cómodo con la aplicacion,trabajeremos en ello");
    }

    @FXML
    private Button botonEnviar;

    @FXML
    public void enviar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) botonEnviar.getScene().getWindow();
            stage.setScene(new Scene(root));
            Main.showAlert("Ventana Emergente", "Encuesta enviada correctamente", "Gracias por realizar la encuesta");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Button volver;

    @FXML
    public void volverbtn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) volver.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

