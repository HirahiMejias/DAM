package Controladores;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Division;
import model.Match;
import org.example.DAO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;

import java.io.IOException;
import java.util.ArrayList;


public class partidosController {
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Match> partidosTable;
    @FXML
    private TableColumn<Match, String> equipoLocalColumn;
    @FXML
    private TableColumn<Match, String> equipoVisitanteColumn;
    @FXML
    private TableColumn<Match, Integer> golesLocalColumn;
    @FXML
    private TableColumn<Match, Integer> golesVisitanteColumn;
    @FXML
    private TableColumn<Match, String> fechaColumn;
    @FXML
    private TableColumn<Match, String> divisionColumn;

    @FXML
    private TableColumn<Match, String> temporadaColumn;

    @FXML
    private TableColumn<Match, String> ganadorColumn;

    @FXML TableColumn<Match,String> idColumn;
    @FXML
    private Button botonBuscar;

    @FXML
    private void initialize() {
        Configuration c = new Configuration().configure();

        try (SessionFactory sf = c.buildSessionFactory()) {
            Session s = sf.openSession();
            equipoLocalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getHomeTeam().toUpperCase()));

            equipoVisitanteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getAwayTeam().toLowerCase()));

            golesLocalColumn.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().getFthg().intValue()).asObject());

            golesVisitanteColumn.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().getFtag()).asObject());

            fechaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatchDate().toString()));

            divisionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getDivision().getName()));

            temporadaColumn.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().getSeason()).asObject().asString());

            ganadorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFtr()));

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject().asString());
            s.close();
            cargarPartidos();
        }
    }


    @FXML
    public void cargarPartidos() {


        Configuration c = new Configuration().configure();

        try (SessionFactory sf = c.buildSessionFactory()) {
            Session session = sf.openSession();
            String query = searchField.getText();

            ArrayList<Match> listaPartidos = (ArrayList<Match>) session.createQuery("FROM Match where homeTeam like :query or awayTeam like :query", Match.class)
                    .setParameter("query", "%" + query + "%")
                    .list();

            for (Match m : listaPartidos) {
                Hibernate.initialize(m.getDivision());
            }


            for (Match con : listaPartidos) {
                System.out.println(con);
            }
            partidosTable.getItems().setAll(listaPartidos);
            session.close();
        }
    }

    @FXML
    public void cargarPorAnyo(){
        Configuration c = new Configuration().configure();

        try (SessionFactory sf = c.buildSessionFactory()) {
            Session session = sf.openSession();
            String query = searchField.getText();

            ArrayList<Match> listaPartidos = (ArrayList<Match>) session.createQuery("FROM Match where season = :query", Match.class)
                    .list();

            for (Match m : listaPartidos) {
                Hibernate.initialize(m.getDivision());
            }


            for (Match con : listaPartidos) {
                System.out.println(con);
            }
            partidosTable.getItems().setAll(listaPartidos);
            session.close();
        }
    }

    @FXML
    public void crearPartido() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/Crear.fxml"));
        Parent root = fxmlLoader.load();

        // Mostrar la nueva ventana
        Stage stage = new Stage();
        stage.setTitle("Crear");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void actualizar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/Actualizar.fxml"));
        Parent root = fxmlLoader.load();

        // Mostrar la nueva ventana
        Stage stage = new Stage();
        stage.setTitle("Crear");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private TextField borrarTf;
    Configuration c = new Configuration().configure();
    DAO<Division, String> divisionDao;
    DAO<Match, Integer> matchDao;

    @FXML
    public void borrarPartido() {
        try (SessionFactory sf = c.buildSessionFactory()) {
            divisionDao = new DAO<>(sf, Division.class);
            matchDao = new DAO<>(sf, Match.class);

            String PartidoId = borrarTf.getText();
            Match partidoToDelete = matchDao.findById(Integer.valueOf(PartidoId));

            if (PartidoId != null) {
                matchDao.delete(partidoToDelete);
                initialize();
                System.out.println("División eliminada exitosamente.");
            } else {
                System.out.println("No se encontró una división con el ID proporcionado.");
            }
        }
    }
}
