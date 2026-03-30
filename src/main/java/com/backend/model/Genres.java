package com.backend.model;

import com.backend.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Genres(Integer genre_id, String type) {

    public static List<Genres> getGenres() throws SQLException {
        Database database = Database.getInstance();
        List<Genres> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(" SELECT * FROM genres ");

        while (res.next()) {
            Genres g = new Genres(
                    res.getInt("genre_id"),
                    res.getString("type")
            );
            results.add(g);
        }

        return results;
    }


    @Override
    public String toString() {
        return type;
    }
}
