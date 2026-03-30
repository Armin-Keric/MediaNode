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

    public static List<Media> plannedByUser = new ArrayList<>();
    public static List<Media> consumedByUser = new ArrayList<>();
    public static List<Media> completedByUser = new ArrayList<>();

    public static List<Media> getLibrary() throws SQLException {
        Database database = Database.getInstance();
        List<Media> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        //return "SELECT * FROM media WHERE type = '" + type + "' ORDER BY title ASC";

        //The user_id 1 is for testing you have to change this afterwards
        ResultSet res = stmt.executeQuery(" SELECT m.* FROM media m JOIN USER_library ul USING (m.id) WHERE ul.status IN ('PLANNING', 'CONSUMING', 'COMPLETED') AND ul.user_id ='" + 1 + "' ");

        plannedByUser.clear();
        consumedByUser.clear();
        completedByUser.clear();

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
                    consumedByUser.add(m);
                    break;

                case "PLANNING":
                    plannedByUser.add(m);
                    break;

                case "COMPLETED":
                    completedByUser.add(m);
                    break;
            }
            results.add(m);
        }

        return results;
    }
}
