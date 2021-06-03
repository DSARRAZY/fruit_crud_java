package com.cefim.fruit;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class FruitJdbcDAO implements CrudDao<Long, Fruit> {

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
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Date date = rs.getDate("expirationDate");
        return new Fruit(id, name, date);
    }

    @Override
    public boolean create(Fruit object) {
        return false;
    }

    @Override
    public Fruit findById(Long id) {
        String query = "SELECT * FROM fruits WHERE id = ?";

        return null;
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
