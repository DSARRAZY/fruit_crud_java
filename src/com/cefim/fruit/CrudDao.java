package com.cefim.fruit;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<ID, T> {

    T create(T object) throws SQLException;

    T findById(ID id);

    List<T> findAll();

    boolean update(T object);

    boolean delete(ID id);

}

