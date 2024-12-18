import java.sql.*;
import java.util.ArrayList;

public class GatitoDAOMySQL implements GatitoDAO {

    private Connection conexion;

    public GatitoDAOMySQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<Gatito> getGatitos() {
        ArrayList<Gatito> gatitos = new ArrayList<>();
        String query = "SELECT * FROM gatitos";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                boolean esAlergico = rs.getBoolean("esAlergico");
                gatitos.add(new Gatito(nombre, edad, esAlergico));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gatitos;
    }

    @Override
    public int deleteGatito(Gatito gatito) {
        String query = "DELETE FROM gatitos WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, gatito.getNombre());
            return pstmt.executeUpdate(); // Devuelve 1 si se eliminó un registro
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Indica que no se eliminó ningún registro
    }

    @Override
    public int addGatito(Gatito gatito) {
        String query = "INSERT INTO gatitos (nombre, edad, esAlergico) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, gatito.getNombre());
            pstmt.setInt(2, gatito.getEdad());
            pstmt.setBoolean(3, gatito.isEsAlergico());
            return pstmt.executeUpdate(); // Devuelve 1 si se insertó un registro
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Indica que no se insertó ningún registro
    }

    @Override
    public int updateGatito(Gatito gatito) {
        String query = "UPDATE gatitos SET edad = ?, esAlergico = ? WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, gatito.getEdad());
            pstmt.setBoolean(2, gatito.isEsAlergico());
            pstmt.setString(3, gatito.getNombre());
            return pstmt.executeUpdate(); // Devuelve 1 si se actualizó un registro
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Indica que no se actualizó ningún registro
    }
}
