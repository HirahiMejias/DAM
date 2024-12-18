package org.example.prototipojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {
    @FXML
    private Button alta;

    @FXML
    public void botonAlta() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Alta2.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) alta.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private Button MostrarButton;

    @FXML
    public void botonAccederMostrar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MostrarServicios.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) MostrarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Button modificarButton;

    @FXML
    public void botonAccederModificar() {
        Main.showAlert("Ventana Emergente", "Modificar Servicio", "Ventana en construcción");
    }

    @FXML
    private Button menuButton;

    @FXML
    public void botonAccederMostrarMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) menuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Button opcionesButton;

    @FXML
    public void accederOpciones() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Opciones.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) menuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Button valoracion;

    @FXML
    public void valorar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Encuesta.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) valoracion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
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

