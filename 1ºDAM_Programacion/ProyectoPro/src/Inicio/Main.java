package inicio;


public class Main {
    public static void main(String[] args) {
        // Conectar a la base de datos
        ConexionBD.conectar();
        
        // Crear una instancia de GestorMenus
        GestorMenus gestorMenus = new GestorMenus();
        
        // Llamar al método mostrarMenuPrincipal()
        gestorMenus.mostrarMenuPrincipal();
        
        // Desconectar de la base de datos al salir
        ConexionBD.desconectar();
    }
}