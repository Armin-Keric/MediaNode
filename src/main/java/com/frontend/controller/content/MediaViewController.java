package com.frontend.controller.content;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MediaViewController implements Initializable {
    public ComboBox<String> addToListComboBox;
    public Label ratingLabel;
    public Label titleLabel;
    public ImageView imageImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading media...");
    }

    public void onAddToListComboBoxClicked(ActionEvent actionEvent) {

    }
}
