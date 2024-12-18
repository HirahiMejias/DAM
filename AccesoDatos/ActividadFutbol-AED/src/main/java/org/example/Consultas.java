package org.example;

import model.Division;
import model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Consultas {
    static SessionFactory sf;

    public static void consulta1() {
        Session session = sf.openSession();
        ArrayList<Match> listaPartidos = (ArrayList<Match>) session.createQuery("FROM Match where division.country='Spain'").list();
        for (Match match : listaPartidos) {
            System.out.println(match.toString());
        }
        session.close();
    }

    public static void consulta2() {
        Session session = sf.openSession();
        ArrayList<Match> listaPartidos = (ArrayList<Match>) session.createQuery("FROM Match where ftag>2").list();
        for (Match match : listaPartidos) {
            System.out.println(match.toString());
        }
        session.close();
    }

    public static void consulta3() {
        Session session = sf.openSession();
        int Maximogoles = (int) session.createQuery("select MAX (ftag) FROM Match").getSingleResult();
        System.out.println(Maximogoles);
        session.close();
    }

    public static void consulta4() {
        Session session = sf.openSession();
        ArrayList<Object[]> listaEquipos = (ArrayList<Object[]>) session.createQuery("select homeTeam, season, count(*) FROM Match group by homeTeam,season order by homeTeam").list();


        for (Object[] o : listaEquipos) {
            String homeTeam = o[0].toString();
            String season = o[1].toString();
            String count = o[2].toString();
            System.out.println(homeTeam + " " + season + " " + count);
        }
        session.close();
    }

    public static void consulta5() {
        Session session = sf.openSession();
        ArrayList<Division> masDe5Goles = (ArrayList<Division>) session.createQuery("FROM Division d " +
                "join fetch d.matchs m where m.ftag > 4 or m.fthg > 4").list();


        for (Division match : masDe5Goles) {
            System.out.println(match.toString());
        }
        session.close();
    }

    public static void consulta6() {
        Session session = sf.openSession();
        System.out.println("Introduce la division: ");
        Scanner scanner = new Scanner(System.in);
        Query q = session.createQuery("FROM Match where division.name like :division and season>2009"); // ?
        q.setParameter("division", scanner.nextLine());
        ArrayList<Match> masDe5Goles = (ArrayList<Match>) q.list();


        for (Match match : masDe5Goles) {
            System.out.println(match.toString());
        }
        session.close();
    }

    public static void consulta7() {
        Session session = sf.openSession();
        ArrayList<Match> masDe5Goles = (ArrayList<Match>) session.createQuery(
                "FROM Match where (homeTeam like 'Tenerife' and awayTeam like 'Zaragoza') or (homeTeam like 'Zaragoza' and awayTeam like 'Tenerife')"
        ).list();

        int tenerifeWins = 0;
        int zaragozaWins = 0;
        for (Match match : masDe5Goles) {
            if (match.getFtr().equals("H")) {
                if (match.getHomeTeam().equals("Tenerife")) {
                    tenerifeWins++;
                } else if (match.getHomeTeam().equals("Zaragoza")) {
                    zaragozaWins++;
                }
            } else if (match.getFtr().equals("A")) {
                if (match.getAwayTeam().equals("Tenerife")) {
                    tenerifeWins++;
                } else if (match.getAwayTeam().equals("Zaragoza")) {
                    zaragozaWins++;
                }
            }

        }
        session.close();

        System.out.println("Tenerife wins: " + tenerifeWins);
        System.out.println("Zaragoza wins: " + zaragozaWins);
    }
}
