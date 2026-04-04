package com.backend.service;

import com.backend.Database;
import com.backend.model.Media;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getActivityLog(int sessionId) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();
        List<String> result = new ArrayList<>();

        String sql = "SELECT m.title, ul.status, ul.score, ul.created_at FROM media m JOIN user_library ul USING(id)" +
                " WHERE ul.user_id = ? ORDER BY ul.created_at DESC";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, AuthService.sessionId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            result.add(rs.getString("title") + " | " + rs.getString("status") + " | " + rs.getObject("created_at"));
        }

        return result;
    }

    //basically for building the Chart in the Profile
    public static XYChart.Series<String, Number> getRatings() throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();
        XYChart.Series<String, Number> result = new XYChart.Series<>();

        String sql = "SELECT score, COUNT(*) AS total FROM user_library WHERE user_id = ? AND score > 0 GROUP BY score ORDER BY score ASC";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, AuthService.sessionId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String value = String.valueOf(rs.getInt("score"));
            int total = rs.getInt("total");
            result.getData().add(new XYChart.Data<>(value, total));
        }

        return result;
    }

    public static List<Media> getRecommendations() throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();
        List<Media> result = new ArrayList<>();

        String sql = "SELECT * FROM (" +
                "  SELECT DISTINCT m.* FROM media m " +
                "  JOIN media_genres mg USING (id) " +
                "  WHERE mg.genre_id IN (" +
                "    SELECT DISTINCT mg2.genre_id FROM user_library ul " +
                "    JOIN media_genres mg2 USING (id) " +
                "    WHERE ul.user_id = ? AND ul.score >= 5" +
                "  ) " +
                "  AND m.id NOT IN (SELECT id FROM user_library WHERE user_id = ?)" +
                ") AS subquery " +
                "ORDER BY RANDOM() LIMIT 10";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, AuthService.sessionId);
        stmt.setInt(2, AuthService.sessionId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Media m = new Media(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getObject("release_date", LocalDate.class),
                    rs.getString("type"),
                    rs.getString("description"),
                    rs.getString("img_url")
            );
            result.add(m);
        }

        return result;
    }
}
