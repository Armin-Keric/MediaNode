package com.backend;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;


public class Database {
    private static Database instance;
    Connection connection;
    private static final Dotenv dotenv = Dotenv.load(); //loads .env file
    private final String USER = dotenv.get("POSTGRES_USER");
    private final String PASSWORD = dotenv.get("POSTGRES_PASSWORD");
    private final String DB_NAME = dotenv.get("POSTGRES_DB");

    private Database() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/" + DB_NAME + "?user=" + USER  + "&password=" + PASSWORD;
        connection = DriverManager.getConnection(url);
    }

    /**
     * POSTGRES_PASSWORD: password
     * POSTGRES_DB: media_collection
     * POSTGRES_USER: user
     * @return
     * @throws SQLException
     */

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
