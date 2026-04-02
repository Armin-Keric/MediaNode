package com.backend.service;

import com.backend.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    public boolean isTaken = false;
    public static int sessionId = 0;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public boolean checkLogin(String username, String password) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String CHECK_STMT = "SELECT user_id, name, password FROM users WHERE name = ? AND password = ?";

        PreparedStatement checkstmt = c.prepareStatement(CHECK_STMT);
        checkstmt.setString(1, username);
        checkstmt.setString(2, password);

        ResultSet rs = checkstmt.executeQuery();

        if (rs.next()) {
            System.out.println("Login successfull...");
            setSessionId(rs.getInt("user_id"));
            System.out.println("Current session: " + getSessionId());

            return true;
        }

        System.out.println("Password doesn't match or user doesn't exist!");

        return false;
    }

    public boolean saveUser(String username, String password) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String CHECK_STMT = "SELECT COUNT(*) FROM users WHERE name = ?";
        String ADD_USER = "INSERT INTO users (name, password) VALUES ( ?, ?)";

        PreparedStatement checkstmt = c.prepareStatement(CHECK_STMT);
        checkstmt.setString(1, username);

        ResultSet rs = checkstmt.executeQuery();

        int count = 0;

        //if the cursor moves...
        if (rs.next()) count = rs.getInt(1);

        if (count > 0) {
            setTaken(true);
            System.out.println("Name is taken...");

            return false;
        }

        PreparedStatement addstmt = c.prepareStatement(ADD_USER);
        addstmt.setString(1, username);
        addstmt.setString(2, password);

        addstmt.executeUpdate();

        System.out.println("User was succesfully added to the DB...");
        System.out.println("You can now login into your account....");

        return true;
    }
}
