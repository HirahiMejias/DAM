package org.example;

import java.lang.module.Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DAO<T,ID > implements DAOinterface<T, ID> {

    private SessionFactory sf;
    private final Class<T> entityClass;

    public DAO(SessionFactory sf,Class<T> entityClass){
        this.sf = sf;
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            session.close();
    }

    @Override
    public void update(T entity) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(entity);
        tx.commit();
        session.close();
    }

    @Override
    public T findById(ID id) {
        Session session = sf.openSession();
        T entity = null;
        try {
            entity = session.get(entityClass, id); // Carga la entidad
            if (entity != null) {
                session.refresh(entity); // Refresca para garantizar que las relaciones estén disponibles
            }
        } finally {
            session.close();
        }
        return entity;
    }


    @Override
    public ArrayList<T> findAll() {
        Session session = sf.openSession();
        ArrayList<T> lista = null;
        try {
            lista = (ArrayList<T>) session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

}
