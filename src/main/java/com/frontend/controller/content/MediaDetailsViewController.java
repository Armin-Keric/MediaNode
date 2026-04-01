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

    public void setMedia(int mediaId) {
        super.setMedia(mediaId);



        try {
            Media tmp = Media.medias("ASC").get(mediaId);

            descriptionTextArea.setText(tmp.getDescription());
            detailsListView.getItems().addAll(
                    tmp.getRelease_date().toString(),
                    tmp.getType()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
