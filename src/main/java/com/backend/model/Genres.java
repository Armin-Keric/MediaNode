package com.backend.model;

import com.backend.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Genres(Integer genre_id, String type) {

    public static List<Genres> getGenres() throws SQLException {
        Database database = Database.getInstance();
        List<Genres> results = new ArrayList<>();
        Connection c = database.getConnection();
        String sql = "SELECT * FROM genres";
        PreparedStatement stmt = c.prepareStatement(sql);
        ResultSet res = stmt.executeQuery();

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
