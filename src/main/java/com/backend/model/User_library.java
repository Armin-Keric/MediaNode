package com.backend.model;

import com.backend.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record User_library(Integer user_id, Integer id) {
    public static List<Media> consuming = new ArrayList<>();
    public static List<Media> completed = new ArrayList<>();
    public static List<Media> planning = new ArrayList<>();


    public static void getUserList() throws SQLException {
        Database database = Database.getInstance();
        List<Media> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();

        consuming.clear();
        planning.clear();
        completed.clear();

        String sql = " SELECT DISTINCT m.*, ul.status FROM media m JOIN user_library ul USING(id) WHERE ul.status IN ('PLANNING','CONSUMING','COMPLETED') AND ul.user_id = 1 ORDER BY m.title  ASC ";
        ResultSet res = stmt.executeQuery(sql);

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
}
