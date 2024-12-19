module com.example.javafxejercicios {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxejercicios to javafx.fxml;
    exports com.example.javafxejercicios;
}