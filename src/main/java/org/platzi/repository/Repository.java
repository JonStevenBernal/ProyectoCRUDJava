package org.platzi.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> findAll() throws SQLException;
    T getById(int id) throws SQLException;
    void update(int id, T t) throws SQLException;
    void save(T t) throws SQLException;
    void delete(T t) throws SQLException;
}
