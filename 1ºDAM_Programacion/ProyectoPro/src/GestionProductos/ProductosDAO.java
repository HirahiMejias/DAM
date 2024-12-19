package GestionProductos;

import Inicio.SQLBuilder;
import Interfaz.InterfazGenerica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO implements InterfazGenerica<Productos, Integer> {
    private Connection conn;

    public ProductosDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insert(Productos producto) {
        String query = SQLBuilder.buildInsertProductoQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getDescripcion());
            stmt.setInt(2, producto.getEan13());
            stmt.setString(3, producto.getKeyRFID());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Productos producto) {
        String query = SQLBuilder.buildUpdateProductoQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getDescripcion());
            stmt.setInt(2, producto.getEan13());
            stmt.setString(3, producto.getKeyRFID());
            stmt.setInt(4, producto.getIdProducto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String query = SQLBuilder.buildDeleteProductoQuery();
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
    public Productos selectById(Integer id) {
        String query = SQLBuilder.buildSelectProductoByIdQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String descripcion = rs.getString("Descripcion");
                int ean13 = rs.getInt("EAN13");
                String keyRFID = rs.getString("KeyRFID");
                return new Productos(id, descripcion, ean13, keyRFID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Productos> selectAll() {
        List<Productos> productosList = new ArrayList<>();
        String query = SQLBuilder.buildSelectAllProductoQuery();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("IdProducto");
                String descripcion = rs.getString("Descripcion");
                int ean13 = rs.getInt("EAN13");
                String keyRFID = rs.getString("KeyRFID");
                Productos producto = new Productos(id, descripcion, ean13, keyRFID);
                productosList.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosList;
    }
}
