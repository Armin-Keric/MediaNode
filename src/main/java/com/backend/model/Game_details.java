package com.backend.model;

import com.backend.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Game_details extends Media {
    private int playtime;
    private String platform;
    private String publisher;

    private boolean multiplayer;

    public Game_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int playtime, String platform, String publisher, boolean multiplayer) {
        super(id, title, release_date, type, description, img_url);
        this.playtime = playtime;
        this.platform = platform;
        this.publisher = publisher;
        this.multiplayer = multiplayer;
    }

    public static Game_details getGameDetails(int mediaId) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT m.*, g.playtime, g.platform, g.publisher, g.multiplayer " +
                "FROM media m JOIN game_details g ON m.id = g.id " +
                "WHERE m.id = ?";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, mediaId);
        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return new Game_details(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url"),
                    res.getInt("playtime"),
                    res.getString("platform"),
                    res.getString("publisher"),
                    res.getBoolean("multiplayer")
            );
        }
        return null;
    }


    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }
}
