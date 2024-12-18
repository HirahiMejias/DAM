package org.example.prototipojavafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AltaController {

    @FXML
    private ComboBox<String> Complementos;
    @FXML
    private Spinner<Integer> spinner1;
    @FXML
    private Spinner<Integer> spinner2;
    @FXML
    private Spinner<Integer> spinner3;
    @FXML
    private Spinner<Integer> spinner4;
    @FXML
    private Spinner<Integer> spinner5;
    @FXML
    private Spinner<Integer> spinner6;

    public void initialize() {
        Complementos.setItems(FXCollections.observableArrayList("Gofio", "Colacao", "Galletas", "Leche"));
        Complementos.getSelectionModel().selectFirst();
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 0);
        spinner1.setValueFactory(valueFactory1);
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 0);
        spinner2.setValueFactory(valueFactory2);
        SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 0);
        spinner3.setValueFactory(valueFactory3);
        SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 0);
        spinner4.setValueFactory(valueFactory4);
        SpinnerValueFactory<Integer> valueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 0);
        spinner5.setValueFactory(valueFactory5);
        SpinnerValueFactory<Integer> valueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 0);
        spinner6.setValueFactory(valueFactory6);

    }



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
    public void botonAccederModificar() {
        Main.showAlert("Ventana Emergente", "Modificar Servicio", "Ventana en construcción");
    }

    @FXML
    private Button mostrarServicios;

    @FXML
    public void botonAccederMostrar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MostrarServicios.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) mostrarServicios.getScene().getWindow();
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

    @FXML
    private Button guardarButton;

    @FXML
    public void botonGuardar() {
        Main.showAlert("Ventana Emergente", "Alta de Servicio", "Información guardada con éxito");
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
