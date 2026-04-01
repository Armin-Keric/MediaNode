package com.backend.model;


import com.backend.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public record Users(Integer user_id, String name, String password, String biography, String img_url) {

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

    //@ToDo

    //we need this shit here for authorization
    //I somehow have to set the current user id from the user that logged in
    /**
    public  Users getSession(String query) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            int x = res.getInt("user_id");
            user_id(x);
        }

        return
    }

    @Override
    public Integer user_id(int x) {
        return user_id;
    }
    **/
}
