package org.example.prototipojavafx;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button button;

    @FXML
    public void botonAcceder() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(new Scene(root));
    }catch (IOException e){
        e.printStackTrace();
    }
}
}
