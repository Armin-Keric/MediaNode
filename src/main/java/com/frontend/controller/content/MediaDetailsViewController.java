package com.frontend.controller.content;

import com.backend.model.Media;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class MediaDetailsViewController extends MediaViewController {
    public ImageView bannerImageView;
    public ImageView imageImageView;
    public TextArea descriptionTextArea;
    public Label titleLabel;
    public ListView<String> detailsListView;

    public void setMedia(Media m) {
        super.setMedia(m);


        descriptionTextArea.setText(m.getDescription());
        detailsListView.getItems().addAll(
                m.getRelease_date().toString(),
                m.getType()
        );
    }
}