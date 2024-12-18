package org.example;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T, K> {
    void add(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(K key) throws SQLException;
    T findById(K key) throws SQLException;
    List<T> findAll() throws SQLException;
}
