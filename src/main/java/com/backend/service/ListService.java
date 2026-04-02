package com.backend.service;

import com.backend.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListService {

    public static void addToList(String status, int media_id, int rating) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "INSERT INTO user_library (user_id, id, status, score) VALUES (?, ?, ?, ?) " +
                "ON CONFLICT (user_id, id) DO UPDATE SET status = EXCLUDED.status, score = EXCLUDED.score";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, AuthService.sessionId);
        stmt.setInt(2, media_id);
        stmt.setString(3, status);

        if (status.equals("PLANNING")) {
            stmt.setInt(4, 0);
        } else {
            stmt.setInt(4, rating);
        }
        stmt.executeUpdate();
    }
}
