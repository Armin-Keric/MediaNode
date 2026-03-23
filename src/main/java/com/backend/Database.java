package com.backend;


import java.sql.*;


public class Database {
    private String url = "mediaLists:postgresql://localhost:5432/media_collection?" + "user=user&password=password";
    private ResultSet resultSet;
    private Statement statement;
    private Queries queries = new Queries();

    protected void openConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            System.out.println("Database connection established");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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


}
