package com.backend;

import com.backend.model.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MediaLists {
    private ArrayList<Media> medias;
    private ArrayList<Media> medias_release;
    private ArrayList<Media> medias_genres;
    private ArrayList<Media> medias_type;
    private final Database database = new Database();

    public void buildMediaList(String order) throws SQLException {
        //The variable comes from JavaFX
        ResultSet resultSet = database.getMediaResultSet(order);

        while (resultSet.next()) {
            Media m = new Media(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getObject("release_date", LocalDate.class),
                    resultSet.getString("type"),
                    resultSet.getString("description"),
                    resultSet.getString("img_url")
            );
            medias.add(m);
        }
    }

    public void buildMediaListByRelease(String order) throws SQLException {
        //The variable comes from JavaFX
        ResultSet resultSet = database.getMediaByReleaseDateResultSet(order);

        while (resultSet.next()) {
            Media m = new Media(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getObject("release_date", LocalDate.class),
                    resultSet.getString("type"),
                    resultSet.getString("description"),
                    resultSet.getString("img_url")
            );
            medias_release.add(m);
        }

    }

    public void buildMediaListByGenre(String genre) throws SQLException {
        //The variable comes from JavaFX
        ResultSet resultSet = database.getGenreResultSet(genre);

        while (resultSet.next()) {
            Media m = new Media(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getObject("release_date", LocalDate.class),
                    resultSet.getString("type"),
                    resultSet.getString("description"),
                    resultSet.getString("img_url")
            );
            medias_genres.add(m);
        }
    }

    public void buildMediaListByType(String type) throws SQLException {
        //The variable comes from JavaFX
        ResultSet resultSet = database.getMediaByTypeResultSet(type);

        while (resultSet.next()) {
            Media m = new Media(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getObject("release_date", LocalDate.class),
                    resultSet.getString("type"),
                    resultSet.getString("description"),
                    resultSet.getString("img_url")
            );
            medias_type.add(m);
        }
    }
}
