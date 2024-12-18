module org.example.prototipojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.prototipojavafx to javafx.fxml;
    exports org.example.prototipojavafx;
}