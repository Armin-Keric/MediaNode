package com.frontend.controller.content;

import com.backend.model.Genres;
import com.backend.model.Media;
import com.frontend.MainController;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
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
        String[] mediaTypes = new String[]{"Anime", "Game", "Music", "Movie", "TVshow"};

        try {
            //mediaGenreComboBox.getItems().addAll(Genres.getGenres());
            mediaYearComboBox.getItems().addAll("ASC", "DESC");
            //mediaTypeComboBox.getItems().addAll(List.of(mediaTypes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < OBJECTS_PER_ROW; ++i) {
            featuredAreaVBox.getChildren().add(new AnchorPane());
            friendAreaVBox.getChildren().add(new AnchorPane());

            MediaViewController tmpFeatured = (MediaViewController) loadView((AnchorPane) featuredAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) friendAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");

            tmpFeatured.setMedia("https://upload.wikimedia.org/wikipedia/commons/d/d3/Kiwi_aka.jpg", "Kiwi");
            tmpFriends.setMedia("https://upload.wikimedia.org/wikipedia/commons/d/d3/Kiwi_aka.jpg", "Kas");
        }
    }

    public String getSelectedTypeOfMedia(javafx.event.ActionEvent actionEvent) {
        String target = mediaTypeComboBox.getSelectionModel().getSelectedItem();
        System.out.println(target);
        return target;
    }

    public String getSelectedGenre(javafx.event.ActionEvent actionEvent) {
        String target = String.valueOf(mediaGenreComboBox.getSelectionModel().getSelectedItem());
        System.out.println(target);
        return target;
    }

    public String getSelectedYear(){
        String target = mediaYearComboBox.getSelectionModel().getSelectedItem();
        System.out.println(target);
        return target;
    }
}
