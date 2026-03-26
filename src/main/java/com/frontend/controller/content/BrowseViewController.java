package com.frontend.controller.content;

import com.backend.model.Genres;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

public class BrowseViewController implements Initializable {
    public TextField searchTextField;
    public ComboBox<String> mediaTypeComboBox;
    public ComboBox<String> mediaGenreComboBox;
    public ComboBox<String> mediaYearComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            mediaGenreComboBox.getItems().addAll(Collections.singletonList(Genres.getGenres()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
