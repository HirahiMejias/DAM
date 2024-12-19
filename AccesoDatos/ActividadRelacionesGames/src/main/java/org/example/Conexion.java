package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexion {
    static SessionFactory sf;

    private Conexion() {
        Configuration c = new Configuration().configure();
        sf = c.buildSessionFactory();

    }

    public static Session getConexion() {
        if (sf == null) {
            new Conexion();
        }
        return sf.openSession();
    }
}