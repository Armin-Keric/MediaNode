package com.backend.model;

import com.backend.Database;
import com.backend.service.AuthService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record User_library(Integer user_id, Integer id, String status, int score) {
    public static List<Media> consuming = new ArrayList<>();
    public static List<Media> completed = new ArrayList<>();
    public static List<Media> planning = new ArrayList<>();


    public static void getUserList(int session_id) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT DISTINCT m.*, ul.status FROM media m JOIN user_library ul USING(id) WHERE ul.status IN ('PLANNING','CONSUMING','COMPLETED') AND ul.user_id = ? ORDER BY m.title ASC";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1,session_id);

        ResultSet res = stmt.executeQuery();

        consuming.clear();
        planning.clear();
        completed.clear();

        while (res.next()) {
            Media m = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url")
            );

            String status = res.getString("status");

            switch (status) {
                case "CONSUMING":
                    consuming.add(m);
                    break;
                case "PLANNING":
                    planning.add(m);
                    break;
                case "COMPLETED":
                    completed.add(m);
                    break;
            }
        }
    }
    public static User_library getUserData(Media m) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT ul.user_id, ul.id, ul.score, ul.status FROM user_library ul WHERE ul.user_id = ? AND ul.id = ?";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, AuthService.sessionId);
        stmt.setInt(2,m.getId());

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return new User_library(
                    res.getInt("user_id"),
                    res.getInt("id"),
                    res.getString("status"),
                    res.getInt("score")
            );
        }
        return null;
    }
}
