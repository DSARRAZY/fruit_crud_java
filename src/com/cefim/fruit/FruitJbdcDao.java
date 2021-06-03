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
        Fruit createdFruit = null;
        String query = "INSERT INTO fruit (name, expirationDate) VALUES (?, ?)";
        Connection connection = ConnectionManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, fruitToCreate.getName());
            pst.setObject(2, fruitToCreate.getExpirationDate());
            pst.execute();

            ResultSet resultSet = pst.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(1);

            createdFruit = findById(id);

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
        return createdFruit;
    }

    @Override
    public Fruit findById(Long id) {
        String query = "SELECT * FROM fruit WHERE id = ?";
        Fruit foundFruit = null;
        try(PreparedStatement pst= ConnectionManager.getConnection().prepareStatement(query) ) {
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            foundFruit = mapToFruit(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundFruit;
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
