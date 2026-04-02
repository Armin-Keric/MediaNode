package com.frontend.controller.content;

import com.backend.model.Media;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;

public class MediaDetailsViewController extends MediaViewController {
    public ImageView bannerImageView;
    public ImageView imageImageView;
    public TextArea descriptionTextArea;
    public Label titleLabel;
    public ListView<String> detailsListView;
    public ComboBox<String> statusComboBox;
    public Slider ratingSlider;
    public HBox recommendedHBox;

    public void setMedia(Media m) {
        super.setMedia(m);

        descriptionTextArea.setText(m.getDescription());
        detailsListView.getItems().addAll(
                m.getRelease_date().toString(),
                m.getType()
        );
    }

    public void onRatingSliderDragOver(DragEvent dragEvent) {

    }
}