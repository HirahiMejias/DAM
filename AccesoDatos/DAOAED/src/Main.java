import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexion = DriverManager.getConnection("localhost","root","123456");

        GatitoDAO gatitoDAO = new GatitoDAOMySQL(conexion);
        // Añade un nuevo gatito
        Gatito nuevoGatito = new Gatito("Garfield", 10, false);
        gatitoDAO.addGatito(nuevoGatito);

        // Actualiza un gatito
        Gatito gatitoActualizado = new Gatito("Garfield", 19, true);
        gatitoDAO.updateGatito(gatitoActualizado);

        // Elimina un gatito
        gatitoDAO.deleteGatito(gatitoActualizado);

        // Muestra todos los gatitos
        for (Gatito g : gatitoDAO.getGatitos()) {
            System.out.println(g);
        }
    }
}