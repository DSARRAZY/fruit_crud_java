package com.cefim.fruit;

import java.sql.*;
import java.time.LocalDate;
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
        LocalDate date = rs.getObject("expirationDate", LocalDate.class);
        return new Fruit(id, name, date);
    }

    @Override
    public Fruit create(Fruit fruitToCreate) {
        String query = "INSERT INTO fruit (name, expirationDate) VALUES (?, ?)";
        Connection connection = ConnectionManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, fruitToCreate.getName());
            pst.setObject(2, fruitToCreate.getExpirationDate());
            pst.execute();

            // Fetching inserted id
            connection.commit();

        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return null;
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
