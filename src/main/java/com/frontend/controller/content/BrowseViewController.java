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
    private List<String> typeOfMedias = new ArrayList<>();
    private String[] mediatypes;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int OBJECTS_PER_ROW = 5;
        int OBJECTS_PER_COL = 5;
        mediatypes = new String[]{"Anime", "Game", "Music", "Movie", "TVshow"};
        typeOfMedias.addAll(List.of(mediatypes));
        // add combobox options (year is tmp)
        // .getClass().getResources(""); doesn't work?
        try {
            mediaGenreComboBox.getItems().addAll(Genres.getGenres());
            mediaYearComboBox.getItems().addAll("ASC", "DESC");
            mediaTypeComboBox.getItems().addAll(typeOfMedias);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < OBJECTS_PER_ROW; ++i) {
            featuredAreaVBox.getChildren().add(new AnchorPane());
            friendAreaVBox.getChildren().add(new AnchorPane());

            loadView((AnchorPane) featuredAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            loadView((AnchorPane) friendAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
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
