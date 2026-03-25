package com.frontend.controller.content;

import com.frontend.MainController;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseViewController extends MainController implements Initializable {
    public TextField searchTextField;
    public ComboBox<String> mediaTypeComboBox;
    public ComboBox<String> mediaGenreComboBox;
    public ComboBox<String> mediaYearComboBox;
    public HBox featuredAreaVBox;
    public HBox friendAreaVBox;
    public GridPane browseAreaVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int OBJECTS_PER_ROW = 5;
        int OBJECTS_PER_COL = 5;

        // add combobox options (year is tmp)
        mediaYearComboBox.getItems().addAll("ASC", "DESC");
        // .getClass().getResources(""); doesn't work?
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/frontend/data/browse-filteroptions.csv"))) {
            mediaTypeComboBox.getItems().addAll(reader.readLine().split(";"));
            mediaGenreComboBox.getItems().addAll(reader.readLine().split(";"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < OBJECTS_PER_ROW; ++i) {
            featuredAreaVBox.getChildren().add(new AnchorPane());
            friendAreaVBox.getChildren().add(new AnchorPane());

            loadView((AnchorPane) featuredAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            loadView((AnchorPane) friendAreaVBox.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
        }
    }
}
