package com.cefim.fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitJbdcDao implements CrudDao<Long, Fruit> {

    @Override
    public boolean create(Fruit object) {
        return false;
    }

    @Override
    public Fruit findById(Long aLong) {
        return null;
    }

    @Override
    public List<Fruit> findAll() {
        List<Fruit> fruitList = new ArrayList<>();

        String query = "SELECT * FROM fruit";
        try (Statement st = ConnectionManager.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                fruitList.add(mapToFruit(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fruitList;
    }

    private Fruit mapToFruit(ResultSet rs) throws SQLException {
        Long id = rs.getLong ("id");
        String name = rs.getString ("name");
        Date date = rs.getDate("expirationDate");
        return new Fruit(id, name, date);
    }

    @Override
    public boolean update(Fruit object) {
        return false;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
