package Controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/partidos.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Gestión de Partidos");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}