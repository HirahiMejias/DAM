package GestionMarcaje;

import Inicio.SQLBuilder;
import Interfaz.InterfazGenerica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarcajesDAO implements InterfazGenerica<Marcajes, Integer> {
    private Connection conn;

    public MarcajesDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insert(Marcajes marcaje) {
        String query = SQLBuilder.buildInsertMarcajeQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, marcaje.getIdProducto());
            stmt.setInt(2, marcaje.getIdAula());
            stmt.setInt(3, marcaje.getIpo());
            stmt.setTimestamp(4, new java.sql.Timestamp(marcaje.getTimeStamp().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Marcajes marcaje) {
        String query = SQLBuilder.buildUpdateMarcajeQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, marcaje.getIdProducto());
            stmt.setInt(2, marcaje.getIdAula());
            stmt.setInt(3, marcaje.getIpo());
            stmt.setTimestamp(4, new java.sql.Timestamp(marcaje.getTimeStamp().getTime()));
            stmt.setInt(5, marcaje.getIdMarcaje());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String query = SQLBuilder.buildDeleteMarcajeQuery();
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
    public Marcajes selectById(Integer id) {
        String query = SQLBuilder.buildSelectMarcajeByIdQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                int idAula = rs.getInt("idAula");
                int ipo = rs.getInt("ipo");
                Date timeStamp = rs.getTimestamp("timeStamp");
                return new Marcajes(id, idProducto, idAula, ipo, timeStamp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Marcajes> selectAll() {
        List<Marcajes> marcajesList = new ArrayList<>();
        String query = SQLBuilder.buildSelectAllMarcajeQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idMarcaje = rs.getInt("idMarcaje");
                int idProducto = rs.getInt("idProducto");
                int idAula = rs.getInt("idAula");
                int ipo = rs.getInt("ipo");
                Date timeStamp = rs.getTimestamp("timeStamp");
                Marcajes marcaje = new Marcajes(idMarcaje, idProducto, idAula, ipo, timeStamp);
                marcajesList.add(marcaje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marcajesList;
    }
}
