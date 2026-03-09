package com.backend;

import java.io.FileNotFoundException;
import java.sql.*;

public class JDBC {
    private String url = "jdbc:postgresql://localhost:5432/media_collection?" + "user=user&password=password";

    protected void openConnection(String query) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()
        ) {
            System.out.println("A");

            //execute Update for Inserts or generally changing values in table
            statement.executeUpdate(query);
            //ResultSet for SELECTS....
            //ResultSet resultSet = statement.executeQuery(search_query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
