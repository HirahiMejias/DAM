package org.example.prototipojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MostrarController {

    @FXML
    private Button inicioMenu;

    @FXML
    public void botonAccederInicio() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) inicioMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Button altaButton;

    @FXML
    public void botonAccederAlta() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Alta2.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) altaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Button mostrarMenu;

    @FXML
    public void botonAccederMostrarMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) mostrarMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Button modificarButton;

    @FXML
    public void botonAccederModificar() {
        Main.showAlert("Ventana Emergente", "Modificar Servicio", "Ventana en construcción");
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
