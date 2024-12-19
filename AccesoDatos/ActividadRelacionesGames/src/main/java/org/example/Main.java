package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Achievement;
import org.model.Game;
import org.model.Genre;
import org.model.Platform;

import javax.naming.ldap.HasControls;
import java.time.LocalDate;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Añadir un juego");
            System.out.println("2. Borrar o añadir logros a un juego");
            System.out.println("3. Borrar un juego y sus logros");
            System.out.println("4. Añadir o quitar un género de un juego");
            System.out.println("5. Añadir o quitar una plataforma de un juego");
            System.out.println("6. Borrar una plataforma y todos los juegos asociados");
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("7. Buscar logros de juegos por nombre");
            System.out.println("8. Listar juegos lanzados antes de un año");
            System.out.println("9. Listar cantidad de juegos por género y año");
            System.out.println("10. Buscar plataformas con juegos en un rango de rating");
            System.out.println("11. Juegos con más logros");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    anyadirJuego();
                    break;
                case 2:
                    borrarAnyadirLogros();
                    break;
                case 3:
                    borrarLogrosJuegos();
                    break;
                case 4:
                    anyadirQuitarGenero();
                    break;
                case 5:
                    anyadirQuitarPlataforma();
                    break;
                case 6:
                    borrarPlataforma();
                    break;
                case 7:
                    logrosJuegos();
                    break;
                case 8:
                    juegosAntesDe();
                    break;
                case 9:
                    juegosPorGenero();
                    break;
                case 10:
                    plataformasPorRating();
                    break;
                case 11:
                    juegosMasLogros();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }


    public static void anyadirJuego() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade nombre del juego");
        String nombre = scanner.nextLine();
        System.out.println("Añade slug del juego");
        String slug = scanner.nextLine();
        System.out.println("Añade rating del juego");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Añade fecha de lanzamiento del juego YYYY-MM-DD");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());


        //cuando veas esto acuerdate que es un punto mas en la practica
        HashSet<Platform> plataformas = new HashSet<>();
        String continuar;
        do {
            System.out.println("Añade nombre de la plataforma");
            String nombrePlataforma = scanner.nextLine();
            Platform plataforma = new Platform();
            plataforma.setName(nombrePlataforma);
            plataformas.add(plataforma);

            System.out.println("¿Quieres añadir otra plataforma? (s/n)");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        Game game = new Game();
        game.setName(nombre);
        game.setSlug(slug);
        game.setRating(rating);
        game.setReleasedDate(fecha);
        game.setPlatforms(plataformas);
        game.setGenres(null);

        session.persist(game);

        transaction.commit();
        session.close();
    }

    public static void borrarAnyadirLogros() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        //leer el juego a modificar
        System.out.println("Añade el id del juego");
        int id = scanner.nextInt();
        Game game = session.get(Game.class, id);
        System.out.println("Desea borrar o añadir logros (b/a)");
        scanner.nextLine();
        String opcion = scanner.nextLine();

        if (opcion.equalsIgnoreCase("b")) {
            System.out.println("Añade id del logro a borrar");
            int idLogro = scanner.nextInt();
            Achievement achievement = session.get(Achievement.class, idLogro);
            game.getAchievements().remove(achievement);
            session.merge(game);


        } else if (opcion.equalsIgnoreCase("a")) {
            System.out.println("Añade nombre del logro");
            String nombre = scanner.nextLine();
            System.out.println("Añade descripcion del logro");
            String descripcion = scanner.nextLine();
            Achievement achievement = new Achievement();
            achievement.setName(nombre);
            achievement.setDescription(descripcion);
            achievement.setGame(game);


            session.persist(achievement);

        }

        transaction.commit();
        session.close();
    }

    public static void borrarLogrosJuegos() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade el id del juego");
        int id = scanner.nextInt();
        Game game = session.get(Game.class, id);
        session.remove(game);

        transaction.commit();
        session.close();
    }

    //    ◦ Permitir añadir un género a un juego, y quitarlo de un juego. El juego NO DEBERÍA
//    desaparecer. ¿Tiene sentido?
    public static void anyadirQuitarGenero() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade el id del juego");
        int id = scanner.nextInt();
        Game game = session.get(Game.class, id);
        System.out.println("Desea añadir o quitar un genero (a/q)");
        scanner.nextLine();
        String opcion = scanner.nextLine();

        if (opcion.equalsIgnoreCase("a")) {
            System.out.println("Añade id del genero a añadir");
            int idGenero = scanner.nextInt();
            Genre genre = session.get(Genre.class, idGenero);
            game.getGenres().add(genre);
            session.merge(game);

        } else if (opcion.equalsIgnoreCase("q")) {
            System.out.println("Añade id del genero a quitar");
            int idGenero = scanner.nextInt();
            Genre genre = session.get(Genre.class, idGenero);
            game.getGenres().remove(genre);
            session.merge(game);

        }

        transaction.commit();
        session.close();
    }

    //◦ Permitir eliminar una plataforma de un juego, y añadir una plataforma a un juego.
    public static void anyadirQuitarPlataforma() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade el id del juego");
        int id = scanner.nextInt();
        Game game = session.get(Game.class, id);
        System.out.println("Desea añadir o quitar una plataforma (a/q)");
        scanner.nextLine();
        String opcion = scanner.nextLine();

        if (opcion.equalsIgnoreCase("a")) {
            System.out.println("Añade id de la plataforma a añadir");
            int idPlataforma = scanner.nextInt();
            Platform platform = session.get(Platform.class, idPlataforma);
            game.getPlatforms().add(platform);
            session.merge(game);

        } else if (opcion.equalsIgnoreCase("q")) {
            System.out.println("Añade id de la plataforma a quitar");
            int idPlataforma = scanner.nextInt();
            Platform platform = session.get(Platform.class, idPlataforma);
            game.getPlatforms().remove(platform);
            session.merge(game);

        }

        transaction.commit();
        session.close();
    }

    //    Permitir borrar una plataforma, y con ello TODOS los juegos que tenga asociados.
//            ¿Tiene sentido?
    public static void borrarPlataforma() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade el id de la plataforma");
        int id = scanner.nextInt();
        Platform platform = session.get(Platform.class, id);
        session.remove(platform);

        transaction.commit();
        session.close();
    }

    //Pedirle un nombre a un usuario, y sacar todos los logros de aquellos juegos que
    //comiencen con ese nombre. Por ejemplo, si introduzco “Kingdom”, me tienen que
    //salir todos los juego
    public static void logrosJuegos() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade el nombre del juego");
        String nombre = scanner.nextLine();
        List<Game> games = session.createQuery("SELECT g FROM Game g WHERE g.name LIKE :nombre")
                .setParameter("nombre", nombre + "%")
                .getResultList();
        for (Game game : games) {
            System.out.println("Juego: " + game.getName());
            for (Achievement achievement : game.getAchievements()) {
                System.out.println("Logro: " + achievement.getName());
            }
        }

        transaction.commit();
        session.close();
    }
    //◦ Pedirle al usuario un año, y devolver todos los juegos que se hayan lanzado antes
    //de dicho año. Para realizar la consulta, usa la función year(fecha), la cual devuelve,
    //como entero, el año de la fecha pasada como parámetro
    public static void juegosAntesDe() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade el año");
        int year = scanner.nextInt();
        List<Game> games = session.createQuery("SELECT g FROM Game g WHERE year(g.releasedDate) < :year")
                .setParameter("year", year)
                .getResultList();
        for (Game game : games) {
            System.out.println("Juego: " + game.getName());
        }

        transaction.commit();
        session.close();
    }
//    Devuelve una lista de la cantidad de juegos de cada género que se lanzaron por
//    año.
    public static void juegosPorGenero() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        List<Object[]> juegos = session.createQuery("SELECT year(juego.releasedDate), g.name, count(*)" +
                        " FROM Genre  g join  g.games juego GROUP BY year(juego.releasedDate), g.name order by year(juego.releasedDate)")
                .getResultList();
        for (Object[] juego : juegos) {
            System.out.println("Año: " + juego[0] + " Genero: " + juego[1] + " Cantidad: " + juego[2]);
        }

        transaction.commit();
        session.close();
    }

//    ◦ Pedirle al usuario dos números decimales, y devolver todas aquellas plataformas
//    que contengan algún juego con una nota entre el rango de valores representado
//    por esos dos números
    public static void plataformasPorRating() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añade el rating minimo");
        double min = scanner.nextDouble();
        System.out.println("Añade el rating maximo");
        double max = scanner.nextDouble();
        List<Platform> platforms = session.createQuery("SELECT p FROM Platform p JOIN p.games g WHERE g.rating BETWEEN :min AND :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        for (Platform platform : platforms) {
            System.out.println("Plataforma: " + platform.getName());
        }

        transaction.commit();
        session.close();
    }

//    Devuelve aquel (o aquellos) juegos que más logros tengan. En HQL, puedes usar la
//    función size(), que funciona igual que en Java. Usa una subconsulta.
    public static void juegosMasLogros() {
        Session session = Conexion.getConexion();
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        List<Game> games = session.createQuery("SELECT g FROM Game g WHERE size(g.achievements) = (SELECT max(size(g.achievements)) FROM Game g)")
                .getResultList();
        for (Game game : games) {
            System.out.println("Juego: " + game.getName() + " Logros: " + game.getAchievements().size());
        }

        transaction.commit();
        session.close();
    }

}