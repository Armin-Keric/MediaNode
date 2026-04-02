package com.backend.model;

import com.backend.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Music_details extends Media {
    private int length;

    private String artist;
    private String album;

    public Music_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int length, String artist, String album) {
        super(id, title, release_date, type, description, img_url);
        this.length = length;
        this.artist = artist;
        this.album = album;
    }

    public static Music_details getMusicDetails(int mediaId) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT m.*, mu.length, mu.artist, mu.album " +
                "FROM media m JOIN music_details mu ON m.id = mu.id " +
                "WHERE m.id = ?";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, mediaId);
        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return new Music_details(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url"),
                    res.getInt("length"), //
                    res.getString("artist"),
                    res.getString("album")
            );
        }
        return null;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
