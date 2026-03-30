package com.backend.model;

import com.backend.Database;
import com.backend.Queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Media {
    private Integer id;
    private String title;
    private LocalDate release_date;
    String type;
    String description;
    String img_url;


    /// //// Pre cached lists for application
    public static List<Media> medias(String order) throws SQLException {
        Database database = Database.getInstance();
        List<Media> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery("SELECT m.* FROM media m  ORDER BY release_date " + order);

        while (res.next()) {
            Media m = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url")
            );
            results.add(m);
        }

        return results;
    }

    public static List<Media> medias_release(String order) throws SQLException {
        Database database = Database.getInstance();
        List<Media> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(" SELECT * FROM media ORDER BY release_date " + order);

        while (res.next()) {
            Media m = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url")
            );
            results.add(m);
        }

        return results;
    }

    public static List<Media> medias_genres(String genre) throws SQLException {
        Database database = Database.getInstance();
        List<Media> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(" SELECT m.* FROM media m " +
                " JOIN media_genres mg ON m.id = mg.media_id " +
                " JOIN genres g ON mg.genre_id = g.genre_id " +
                " WHERE g.type = '" + genre + "'");

        while (res.next()) {
            Media m = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url")
            );
            results.add(m);
        }

        return results;
    }

    public static List<Media> medias_type(String type) throws SQLException {
        Database database = Database.getInstance();
        List<Media> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(" SELECT * FROM media WHERE type = '" + type + "' ORDER BY title ASC ");

        while (res.next()) {
            Media m = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url")
            );
            results.add(m);
        }

        return results;
    }

    public static List<Media> getCurrentData(String typeOfMedia, String genre, String year) throws SQLException {
        Database database = Database.getInstance();
        List<Media> results = new ArrayList<>();
        Connection c = database.getConnection();
        Statement stmt = c.createStatement();

        String sql = " SELECT DISTINCT m.* FROM media m " +
                " LEFT JOIN media_genres mg ON m.id = mg.id " +
                " LEFT JOIN genres g ON mg.genre_id = g.genre_id " +
                " WHERE ('" + typeOfMedia + "' = 'ALL' OR m.type = '" + typeOfMedia + "') " +
                " AND ('" + genre + "' = 'ALL' OR g.type = '" + genre + "') ";

        if ("DESC".equalsIgnoreCase(year)) {
            sql += " ORDER BY m.release_date DESC";
        } else {
            sql += " ORDER BY m.release_date ASC";
        }

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
            results.add(m);
        }

        return results;
    }

    /// ////////


    public Media(Integer id, String title, LocalDate release_date, String type, String description, String img_url) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.type = type;
        this.description = description;
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
