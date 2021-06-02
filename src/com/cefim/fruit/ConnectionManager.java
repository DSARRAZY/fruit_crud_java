package com.cefim.fruit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/bdd_java";
    private static final String USER = "root";
    private static final String PASSWORD = "Damien81!";

    private static Connection connection;

    private ConnectionManager(){

    }

    public static Connection getConnection() {
        if(connection == null) {
            try {
                loadDriver();
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Driver MySQL introuvable");
        }
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
