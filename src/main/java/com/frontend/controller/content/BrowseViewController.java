package com.frontend.controller.content;

import com.backend.model.Genres;
import com.backend.model.Media;
import com.backend.model.User_library;
import com.frontend.MainController;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BrowseViewController extends MainController implements Initializable {
    public TextField searchTextField;
    public ComboBox<String> mediaTypeComboBox;
    public ComboBox<Genres> mediaGenreComboBox;
    public ComboBox<String> mediaYearComboBox;
    public HBox featuredAreaVBox;
    public HBox friendAreaVBox;
    public GridPane browseAreaVBox;
    private Media m;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int OBJECTS_PER_ROW = 5;
        int OBJECTS_PER_COL = 5;
        // tmp
        String[] mediaTypes = new String[]{"ALL", "Anime", "Game", "Music", "Movie", "TVshow"};

        try {
            Genres fakeGenre = new Genres(null, "ALL");
            mediaGenreComboBox.getItems().add(fakeGenre);
            mediaGenreComboBox.getItems().addAll(Genres.getGenres());
            mediaGenreComboBox.setValue(mediaGenreComboBox.getItems().getFirst());

            mediaYearComboBox.getItems().addAll("ASC", "DESC");
            mediaYearComboBox.setValue(mediaYearComboBox.getItems().getFirst());

            mediaTypeComboBox.getItems().addAll(List.of(mediaTypes));
            mediaTypeComboBox.setValue(mediaTypeComboBox.getItems().getFirst());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < OBJECTS_PER_ROW; ++i) {
            featuredAreaVBox.getChildren().add(new AnchorPane());
            friendAreaVBox.getChildren().add(new AnchorPane());

            MediaViewController tmpFeatured = (MediaViewController) loadView((AnchorPane) featuredAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) friendAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");

            try {

                tmpFeatured.setMedia(Media.medias_type("Game").get(i).getImg_url(), Media.medias_type("Game").get(i).getTitle(), "10/10");

                //the media shown should always be sorted by date at the start...
                tmpFriends.setMedia(Media.medias("ASC").get(i).getImg_url(), Media.medias("ASC").get(i).getTitle(), "10/10");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateLists() throws SQLException {
        int OBJECTS_PER_ROW = 5;
        String typeOfMedia = mediaTypeComboBox.getSelectionModel().getSelectedItem();
        String genre = String.valueOf(mediaGenreComboBox.getSelectionModel().getSelectedItem());
        String year = mediaYearComboBox.getSelectionModel().getSelectedItem();


        friendAreaVBox.getChildren().clear();
        List<Media> currentData = Media.getCurrentData(typeOfMedia, genre, year);


        for (int i = 0; i < OBJECTS_PER_ROW; ++i) {
            featuredAreaVBox.getChildren().add(new AnchorPane());
            friendAreaVBox.getChildren().add(new AnchorPane());

            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) friendAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            tmpFriends.setMedia(currentData.get(i).getImg_url(), currentData.get(i).getTitle(), "10/10");

            //tmpFriends.setMedia(,,);
        }
    }

    public void getSelectedTypeOfMedia(javafx.event.ActionEvent actionEvent) throws SQLException {
        updateLists();
    }

    public void getSelectedGenre(javafx.event.ActionEvent actionEvent) throws SQLException {
        updateLists();
    }

    public void getSelectedYear() throws SQLException {
        updateLists();
    }
}
