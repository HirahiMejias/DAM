package com.example.javafxejercicios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class EtiquetaParpadeante extends Application {
    private String texto = "";

    @Override
    public void start(Stage escenario) {
        StackPane lienzo = new StackPane();
        Label etiqueta = new Label("HOLA PATRONES!!");
        lienzo.getChildren().add(etiqueta);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (etiqueta.getText().trim().length() == 0)
                            texto = "HOLA PATRONES!!";
                        else
                            texto = " ";
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                etiqueta.setText(texto);
                            }
                        });
                        Thread.sleep(200);
                    }
                } catch (
                        InterruptedException ex) {
                }
            }
        }).start();
        Scene escena = new Scene(lienzo, 800, 600);
        escenario.setTitle("Texto loco");
        escenario.setScene(escena);
        escenario.show();
    }
}



