package GestionAulas;

import Inicio.SQLBuilder;
import Interfaz.InterfazGenerica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO implements InterfazGenerica<Aula, Integer> {
    private Connection conn;

    public AulaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insert(Aula aula) {
        String query = SQLBuilder.buildInsertAulaQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, aula.getNumeracion());
            stmt.setString(2, aula.getDescripcion());
            stmt.setString(3, aula.getIp());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Aula aula) {
        String query = SQLBuilder.buildUpdateAulaQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, aula.getNumeracion());
            stmt.setString(2, aula.getDescripcion());
            stmt.setString(3, aula.getIp());
            stmt.setInt(4, aula.getIdAula());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String query = SQLBuilder.buildDeleteAulaQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Aula selectById(Integer id) {
        String query = SQLBuilder.buildSelectAulaByIdQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String numeracion = rs.getString("Numeracion");
                String descripcion = rs.getString("Descripcion");
                String ip = rs.getString("IP");
                return new Aula(id, numeracion, descripcion, ip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Aula> selectAll() {
        List<Aula> aulas = new ArrayList<>();
        String query = SQLBuilder.buildSelectAllAulaQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("idAula");
                String numeracion = rs.getString("Numeracion");
                String descripcion = rs.getString("Descripcion");
                String ip = rs.getString("IP");
                Aula aula = new Aula(id, numeracion, descripcion, ip);
                aulas.add(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aulas;
    }
}
