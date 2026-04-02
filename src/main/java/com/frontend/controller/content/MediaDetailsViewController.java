package com.frontend.controller.content;

import com.backend.Database;
import com.backend.model.*;
import com.backend.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import com.backend.model.Media;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MediaDetailsViewController extends MediaViewController implements Initializable {
    public ImageView bannerImageView;
    public ImageView imageImageView;
    public TextArea descriptionTextArea;
    public Label titleLabel;
    public ListView<String> detailsListView;
    public ComboBox<String> statusComboBox;
    public Slider ratingSlider;
    public HBox recommendedHBox;
    public Label currentRating;
    private Media currentMedia;
    private int rating;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusComboBox.getItems().addAll(
                "PLANNING",
                "CONSUMING",
                "COMPLETED"
        );
        statusComboBox.getSelectionModel().selectFirst();
        //currentRating.setText("Current rating: " + rating);
    }

    public void setMedia(Media m) throws SQLException {
        this.currentMedia = m;
        try {
            super.setMedia(m);
            //User_library.getUserScore(m);

            switch (m.getType()) {
                case "Game":
                    int playtime = Game_details.getGameDetails(m.getId()).getPlaytime() / 3600;
                    descriptionTextArea.setText(m.getDescription());
                    detailsListView.getItems().addAll(
                            "Type: " + m.getType(),
                            "Release-Date: " + m.getRelease_date().toString(),
                            "Platform: " + Game_details.getGameDetails(m.getId()).getPlatform(),
                            "Publisher: " + Game_details.getGameDetails(m.getId()).getPublisher(),
                            "Avg. Playtime: " + playtime + "h"
                    );

                    break;

                case "TVshow":
                case "Anime":
                    descriptionTextArea.setText(m.getDescription());
                    detailsListView.getItems().addAll(
                            "Type: " + m.getType(),
                            "Release-Date: " + m.getRelease_date().toString(),
                            "Episodes: " + TVshow_details.getTVShowDetails(m.getId()).getEpisodeCount(),
                            "Director: " + TVshow_details.getTVShowDetails(m.getId()).getDirector(),
                            "Seasons: " + TVshow_details.getTVShowDetails(m.getId()).getSeasons(),
                            "Actors: " + TVshow_details.getTVShowDetails(m.getId()).getActors()
                    );
                    break;
                case "Music":
                    int length_mu = Music_details.getMusicDetails(m.getId()).getLength() / 60;

                    descriptionTextArea.setText(m.getDescription());
                    detailsListView.getItems().addAll(
                            "Type: " + m.getType(),
                            "Release-Date: " + m.getRelease_date().toString(),
                            "Length: " + length_mu + "min",
                            "Artist: " + Music_details.getMusicDetails(m.getId()).getArtist(),
                            "Album: " + Music_details.getMusicDetails(m.getId()).getAlbum()
                    );

                    break;

                case "Movie":
                    int length_mo = Movie_details.getMovieDetails(m.getId()).getLength() / 3600;
                    int length_mo_min = (Movie_details.getMovieDetails(m.getId()).getLength() % 3600) / 60;

                    descriptionTextArea.setText(m.getDescription());
                    detailsListView.getItems().addAll(
                            "Type: " + m.getType(),
                            "Release-Date: " + m.getRelease_date().toString(),
                            "Length: " + length_mo + "h" + length_mo_min + "min",
                            "Director: " + Movie_details.getMovieDetails(m.getId()).getDirector(),
                            "Actors: " + Movie_details.getMovieDetails(m.getId()).getActors()
                    );

                    break;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRatingSliderDragOver(DragEvent dragEvent) {

    }

    //makes the entries e.g. PLANNING, CONSUMING etc
    public void addToMediaList(ActionEvent actionEvent) throws SQLException {
        String status = statusComboBox.getSelectionModel().getSelectedItem();
        rating = (int) ratingSlider.getValue();


        if (AuthService.sessionId == 0) {
            System.out.println("Nicht eingeloggt");

        } else {
            Database database = Database.getInstance();
            Connection c = database.getConnection();

            String sql = "INSERT INTO user_library (user_id, id, status, score) VALUES (?, ?, ?, ?) " +
                    "ON CONFLICT (user_id, id) DO UPDATE SET status = EXCLUDED.status, score = EXCLUDED.score";

            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, AuthService.sessionId);
            stmt.setInt(2, currentMedia.getId());
            stmt.setString(3, status);

            if (status.equals("PLANNING")) {
                stmt.setInt(4, 0);
            } else {
                stmt.setInt(4, rating);
            }
            stmt.executeUpdate();
            currentRating.setText("Current Rating: " + rating);
        }
    }
}