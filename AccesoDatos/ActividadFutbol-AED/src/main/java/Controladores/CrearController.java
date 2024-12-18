package Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Division;
import model.Match;
import org.example.DAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class CrearController {

    Configuration c = new Configuration().configure();
    DAO<Division, String> divisionDao;
    DAO<Match, Integer> matchDao;
    @FXML
    private TextField homeTf;
    @FXML
    private TextField awayTf;
    @FXML
    private Spinner<Integer> homeSpinner;
    @FXML
    private Spinner<Integer> awaySpinner;
    @FXML
    private ComboBox<String> resultadoCombo;
    @FXML
    private TextField seasonTf;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField division;


    @FXML
    void initialize() {
        resultadoCombo.getItems().addAll("Local", "Visitante", "Empate");
        homeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        awaySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));

    }

    @FXML
    void crearPartido() {
        try (SessionFactory sf = c.buildSessionFactory()) {
            divisionDao = new DAO<>(sf, Division.class);
            matchDao = new DAO<>(sf, Match.class);

            String divisionId = division.getText();
            Division matchDivision = divisionDao.findById(String.valueOf(divisionId));

            if (matchDivision != null) {
                Match match = new Match();
                match.setDivision(matchDivision);

                match.setMatchDate(fecha.getValue());
                match.setHomeTeam(homeTf.getText());
                match.setAwayTeam(awayTf.getText());
                match.setFthg(Double.valueOf(homeSpinner.getValue()));
                match.setFtag(awaySpinner.getValue());
                match.setFtr(resultadoCombo.getValue());
                match.setSeason(Integer.parseInt(seasonTf.getText()));

                matchDao.create(match);
                System.out.println("Partido creado exitosamente.");
            } else {
                System.out.println("La división no existe.");
            }
        }
    }


}
