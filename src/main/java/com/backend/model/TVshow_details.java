package com.backend.model;

import com.backend.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TVshow_details extends Media {
    private int episodeCount;
    private String director;
    private int seasons;
    private String actors;
    private String actors_img;

    public TVshow_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int episodeCount, String director, int seasons, String actors, String acotrsImg) {
        super(id, title, release_date, type, description, img_url);
        this.episodeCount = episodeCount;
        this.director = director;
        this.seasons = seasons;
        this.actors = actors;
        actors_img = acotrsImg;
    }

    public static TVshow_details getTVShowDetails(int mediaId) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT m.*, tv.episodecount, tv.director, tv.seasons, tv.actors , tv.actors_img " +
                "FROM media m JOIN tvshow_details tv ON m.id = tv.id " +
                "WHERE m.id = ?";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, mediaId);
        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return new TVshow_details(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url"),
                    res.getInt("episodecount"), //
                    res.getString("director"),
                    res.getInt("seasons"),
                    res.getString("actors"),
                    res.getString("actors_img")
            );
        }
        return null;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getAcotrs_img() {
        return actors_img;
    }

    public void setAcotrs_img(String acotrs_img) {
        this.actors_img = acotrs_img;
    }
}
