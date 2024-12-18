module ActividadFutbol.AED {
    requires jakarta.persistence;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.hibernate.orm.core;
    requires java.naming;



    opens model to org.hibernate.orm.core;
    exports Controladores to javafx.graphics;
    opens Controladores to javafx.fxml; // Esto permite la reflexión necesaria para cargar las clases del FXML.
}
