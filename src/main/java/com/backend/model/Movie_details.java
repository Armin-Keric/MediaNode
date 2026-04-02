package com.backend.model;

import com.backend.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Movie_details extends Media {
    private int length;
    private String director;
    private String actors;
    private String actors_img;

    public Movie_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int length, String director, String actors, String actorsImg) {
        super(id, title, release_date, type, description, img_url);
        this.length = length;
        this.director = director;
        this.actors = actors;
        actors_img = actorsImg;
    }

    public static Movie_details getMovieDetails(int mediaId) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT m.*, mo.length, mo.director, mo.actors, mo.actors_img " +
                "FROM media m JOIN movie_details mo ON m.id = mo.id " +
                "WHERE m.id = ?";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, mediaId);
        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return new Movie_details(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url"),
                    res.getInt("length"),
                    res.getString("director"),
                    res.getString("actors"),
                    res.getString("actors_img")
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getActors_img() {
        return actors_img;
    }

    public void setActors_img(String actors_img) {
        this.actors_img = actors_img;
    }
}


