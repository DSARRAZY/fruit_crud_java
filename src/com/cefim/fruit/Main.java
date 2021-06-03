package com.cefim.fruit;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // REQUETE 1
       FruitJdbcDAO fruitJbdcDao = new FruitJdbcDAO();
        List<Fruit> fruitList = fruitJbdcDao.findAll();

        for (Fruit f: fruitList) {
            System.out.println("ID " + f.getId() + " Fruit : " + f.getName() + " Date exp : " + f.getExpirationDate());
        }

//       Fruit fruit = new Fruit("Kiwi", new Date());
//       FruitJbdcDao.create(fruit);



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


