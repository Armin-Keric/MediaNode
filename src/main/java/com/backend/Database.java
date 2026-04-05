package com.backend;


import java.sql.*;



public class Database {
    private static Database instance;
    Connection connection;

    private Database() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/media_collection?" + "user=user&password=password";
        connection = DriverManager.getConnection(url);
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
