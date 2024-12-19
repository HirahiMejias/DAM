package Inicio;

public class SQLBuilder {
    // Consultas SQL para Aula
    public static final String INSERT_AULA = "INSERT INTO Aula (Numeracion, Descripcion, IP) VALUES (?, ?, ?)";
    public static final String DELETE_AULA = "DELETE FROM Aula WHERE idAula = ?";
    public static final String UPDATE_AULA = "UPDATE Aula SET Numeracion = ?, Descripcion = ?, IP = ? WHERE idAula = ?";
    public static final String SELECT_AULA_BY_ID = "SELECT * FROM Aula WHERE idAula = ?";
    public static final String SELECT_ALL_AULA = "SELECT * FROM Aula";

    // Consultas SQL para Marcajes
    public static final String INSERT_MARCAJE = "INSERT INTO Marcaje (idProducto, idAula, ipo, timeStamp) VALUES (?, ?, ?, ?)";
    public static final String DELETE_MARCAJE = "DELETE FROM Marcaje WHERE idMarcaje = ?";
    public static final String UPDATE_MARCAJE = "UPDATE Marcaje SET idProducto = ?, idAula = ?, ipo = ?, timeStamp = ? WHERE idMarcaje = ?";
    public static final String SELECT_MARCAJE_BY_ID = "SELECT * FROM Marcaje WHERE idMarcaje = ?";
    public static final String SELECT_ALL_MARCAJE = "SELECT * FROM Marcaje";
    
    // Consultas SQL estáticas para la tabla de productos
    public static final String INSERT_PRODUCTO = "INSERT INTO Productos (Descripcion, EAN13, KeyRFID) VALUES (?, ?, ?)";
    public static final String DELETE_PRODUCTO = "DELETE FROM Productos WHERE IdProducto = ?";
    public static final String UPDATE_PRODUCTO = "UPDATE Productos SET Descripcion = ?, EAN13 = ?, KeyRFID = ? WHERE IdProducto = ?";
    public static final String SELECT_PRODUCTO_BY_ID = "SELECT * FROM Productos WHERE IdProducto = ?";
    public static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM Productos";
    
    // Métodos para construir consultas de Aula
    public static String buildInsertAulaQuery() {
        return INSERT_AULA;
    }

    public static String buildDeleteAulaQuery() {
        return DELETE_AULA;
    }

    public static String buildUpdateAulaQuery() {
        return UPDATE_AULA;
    }

    public static String buildSelectAulaByIdQuery() {
        return SELECT_AULA_BY_ID;
    }

    public static String buildSelectAllAulaQuery() {
        return SELECT_ALL_AULA;
    }

    // Métodos para construir consultas de Marcajes
    public static String buildInsertMarcajeQuery() {
        return INSERT_MARCAJE;
    }

    public static String buildDeleteMarcajeQuery() {
        return DELETE_MARCAJE;
    }

    public static String buildUpdateMarcajeQuery() {
        return UPDATE_MARCAJE;
    }

    public static String buildSelectMarcajeByIdQuery() {
        return SELECT_MARCAJE_BY_ID;
    }

    public static String buildSelectAllMarcajeQuery() {
        return SELECT_ALL_MARCAJE;
    }
    
    // Método para construir consulta de inserción de producto
    public static String buildInsertProductoQuery() {
        return INSERT_PRODUCTO;
    }

    // Método para construir consulta de eliminación de producto
    public static String buildDeleteProductoQuery() {
        return DELETE_PRODUCTO;
    }

    // Método para construir consulta de actualización de producto
    public static String buildUpdateProductoQuery() {
        return UPDATE_PRODUCTO;
    }

    // Método para construir consulta de selección de producto por ID
    public static String buildSelectProductoByIdQuery() {
        return SELECT_PRODUCTO_BY_ID;
    }

    // Método para construir consulta de selección de todos los productos
    public static String buildSelectAllProductoQuery() {
        return SELECT_ALL_PRODUCTOS;
    }
}