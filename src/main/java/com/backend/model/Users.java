package com.backend.model;


import com.backend.Database;
import com.backend.service.AuthService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public record Users(Integer user_id, String name, String password, String biography, String img_url) {

    //returns a list of the users... we don't use this method right now though
    public static List<Users> getUsers() throws SQLException {
        Database database = Database.getInstance();
        List<Users> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM users");

        while (res.next()) {
            Users u = new Users(
                    res.getInt("user_id"),
                    res.getString("name"),
                    res.getString("password"),
                    res.getString("biography"),
                    res.getString("img_url")
            );
            results.add(u);
        }

        return results;
    }

    /**
     * using this method just for setting the username at the profile view
     *
     * @return
     * @throws SQLException
     */
    public static String getUser() throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT u.name FROM users u WHERE user_id = ?";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, AuthService.sessionId);

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return res.getString("name");
        }
        return null;
    }
}
