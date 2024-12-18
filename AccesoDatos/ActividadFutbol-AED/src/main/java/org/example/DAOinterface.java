package org.example;


import java.util.List;

public interface DAOinterface <T,ID> {
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();
}
