package org.example;

import model.Division;
import model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Console;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration c = new Configuration().configure();
        DAO<Division, String> divisionDao;
        DAO<Match, Integer> matchDao;

        try (SessionFactory sf = c.buildSessionFactory()) {
            divisionDao = new DAO<>(sf, Division.class);
            matchDao = new DAO<>(sf, Match.class);
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Crear División");
                System.out.println("2. Leer División por ID");
                System.out.println("3. Actualizar División");
                System.out.println("4. Eliminar División");
                System.out.println("5. Listar Todas las Divisiones");
                System.out.println("6. Crear Match (Partido)");
                System.out.println("7. Listar Matches por División");
                System.out.println("8. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1: // Crear División
                        System.out.print("Ingrese el ID de la división: ");
                        String divisionId = scanner.nextLine();
                        System.out.print("Ingrese el nombre de la división: ");
                        String name = scanner.nextLine();
                        System.out.print("Ingrese el país de la división: ");
                        String country = scanner.nextLine();

                        Division division = new Division();
                        division.setDivision(divisionId);
                        division.setName(name);
                        division.setCountry(country);

                        divisionDao.create(division);
                        System.out.println("División creada exitosamente.");
                        break;

                    case 2: // Leer División por ID
                        Session s = sf.openSession();
                        System.out.print("Ingrese el ID de la división: ");
                        divisionId = scanner.nextLine();
                        Division foundDivision = divisionDao.findById(divisionId);
                        if (foundDivision != null) {
                            System.out.println("División encontrada: " + foundDivision);
                        } else {
                            System.out.println("No se encontró una división con el ID proporcionado.");
                        }
                        s.close();
                        break;

                    case 3: // Actualizar División
                        System.out.print("Ingrese el ID de la división a actualizar: ");
                        divisionId = scanner.nextLine();
                        Division divisionToUpdate = divisionDao.findById(divisionId);

                        if (divisionToUpdate != null) {
                            System.out.print("Ingrese el nuevo nombre de la división: ");
                            divisionToUpdate.setName(scanner.nextLine());
                            System.out.print("Ingrese el nuevo país de la división: ");
                            divisionToUpdate.setCountry(scanner.nextLine());
                            divisionDao.update(divisionToUpdate);
                            System.out.println("División actualizada exitosamente.");
                        } else {
                            System.out.println("No se encontró una división con el ID proporcionado.");
                        }
                        break;

                    case 4: // Eliminar División
                        System.out.print("Ingrese el ID de la división a eliminar: ");
                        divisionId = scanner.nextLine();
                        Division divisionToDelete = divisionDao.findById(divisionId);

                        if (divisionToDelete != null) {
                            divisionDao.delete(divisionToDelete);
                            System.out.println("División eliminada exitosamente.");
                        } else {
                            System.out.println("No se encontró una división con el ID proporcionado.");
                        }
                        break;

                    case 5: // Listar Todas las Divisiones
                        System.out.println("Listando todas las divisiones:");
                        for (Division d : divisionDao.findAll()) {
                            System.out.println(d);
                        }
                        break;

                    case 6: // Crear Match
                        System.out.print("Ingrese el ID de la división: ");
                        divisionId = scanner.nextLine();
                        Division matchDivision = divisionDao.findById(divisionId);

                        if (matchDivision != null) {
                            Match match = new Match();
                            match.setDivision(matchDivision);

                            System.out.print("Ingrese la fecha del partido (YYYY-MM-DD): ");
                            match.setMatchDate(LocalDate.parse(scanner.nextLine()));
                            System.out.print("Ingrese el equipo local: ");
                            match.setHomeTeam(scanner.nextLine());
                            System.out.print("Ingrese el equipo visitante: ");
                            match.setAwayTeam(scanner.nextLine());
                            System.out.print("Ingrese los goles del equipo local: ");
                            match.setFthg(scanner.nextDouble());
                            System.out.print("Ingrese los goles del equipo visitante: ");
                            match.setFtag(scanner.nextInt());
                            scanner.nextLine(); // Consumir salto de línea
                            System.out.print("Ingrese el resultado del partido (FTR): ");
                            match.setFtr(scanner.nextLine());
                            System.out.print("Ingrese la temporada: ");
                            match.setSeason(scanner.nextInt());

                            matchDao.create(match);
                            System.out.println("Partido creado exitosamente.");
                        } else {
                            System.out.println("La división no existe.");
                        }
                        break;

                    case 7: // Listar Matches por División
                         s = sf.openSession();

                        System.out.print("Ingrese el ID de la división: ");
                        divisionId = scanner.nextLine();
                        Division divisionWithMatches = s.get(Division.class,divisionId);//;divisionDao.findById(divisionId);

                        if (divisionWithMatches != null) {
                            System.out.println("Listando partidos de la división " + divisionWithMatches.getName() + ":");
                            for (Match m : divisionWithMatches.getMatchs()) {
                                System.out.println(m);
                            }
                        } else {
                            System.out.println("No se encontró una división con el ID proporcionado.");
                        }
                        s.close();
                        break;

                    case 8: // Salir
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            } while (opcion != 8);
        }
    }
}
