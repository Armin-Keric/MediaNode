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

    /**
     //Übergabe verläuft im JavaFX Teil!
     public ResultSet getGenreResultSet(String genre) throws SQLException {
     String query = queries.getMediaByGenre(genre);
     return statement.executeQuery(query);
     }

     public ResultSet getMediaResultSet(String order) throws SQLException {
     String query = queries.getMedia(order);
     return statement.executeQuery(query);
     }

     public ResultSet getMediaByTypeResultSet(String type) throws SQLException {
     String query = queries.getMediaByType(type);
     return statement.executeQuery(query);
     }

     public ResultSet getMediaByReleaseDateResultSet(String order) throws SQLException {
     String query = queries.getMediaByDate(order);
     return statement.executeQuery(query);
     }
     **/
}
