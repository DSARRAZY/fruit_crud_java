package com.cefim.fruit;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        // REQUETE READ
       FruitJdbcDAO fruitJbdcDao = new FruitJdbcDAO();
        List<Fruit> fruitList = fruitJbdcDao.findAll();

        for (Fruit f: fruitList) {
            System.out.println("ID " + f.getId() + " Fruit : " + f.getName() + " Date exp : " + f.getExpirationDate());
        }

        // REQUETE CREATE
        Fruit fruit = new Fruit("Kiwi", LocalDate.now());
        Fruit fruitCreated = fruitJbdcDao.create(fruit);

        System.out.println("Le nouveau fruit avec l'id " + fruitCreated.getId()+ " a été ajouté");

        ConnectionManager.closeConnection();




//        // REQUETE 2
//        String querySql = "SELECT * FROM fruit WHERE id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(querySql)) {
//            preparedStatement.setLong(1, 10L);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            System.out.println(resultSet.getInt("id"));
//            System.out.println(resultSet.getString("name"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }


}


