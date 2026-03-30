package com.frontend.controller.content;

import com.frontend.MainController;
import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController extends MainController implements Initializable {

    public ImageView mainCarouselImageView;
    public HBox paginationContainer;

    private final List<String> imagePaths = new ArrayList<>();
    private final ToggleGroup dotGroup = new ToggleGroup();
    private int currentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Test-Path
        imagePaths.add("/com/frontend/view/media/test1.jpg");
        imagePaths.add("/com/frontend/view/media/test2.jpg");
        imagePaths.add("/com/frontend/view/media/test3.jpg");
        imagePaths.add("/com/frontend/view/media/test4.jpg");
        imagePaths.add("/com/frontend/view/media/test5.jpg");

        setupPagination();
        updateUI(false);
    }

    private void setupPagination() {
        paginationContainer.getChildren().clear();
        for (int i = 0; i < imagePaths.size(); i++) {
            RadioButton dot = new RadioButton();
            dot.setToggleGroup(dotGroup);
            dot.getStyleClass().add("pagination-dot"); // CSS Class

            // save Index for next click
            final int targetIndex = i;
            dot.setOnAction(e -> {
                currentIndex = targetIndex;
                updateUI(true);
            });

            paginationContainer.getChildren().add(dot);
        }
    }

    public void onNextClicked() {
        if (imagePaths.isEmpty()) return;
        currentIndex = (currentIndex + 1) % imagePaths.size();
        updateUI(true);
    }

    public void onPreviousClicked() {
        if (imagePaths.isEmpty()) return;
        currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
        updateUI(true);
    }

    private void updateUI(boolean useFade) {
        if (imagePaths.isEmpty()) return;

        if (useFade) {
            // 1. Fade Out
            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), mainCarouselImageView);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // 2. change Image
                loadImage();
                // 3. Fade In
                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), mainCarouselImageView);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
        } else {
            loadImage();
        }

        if (paginationContainer.getChildren().size() > currentIndex) {
            ((RadioButton) paginationContainer.getChildren().get(currentIndex)).setSelected(true);
        }
    }

    private void loadImage() {
        try {
            Image img = new Image(getClass().getResourceAsStream(imagePaths.get(currentIndex)));
            mainCarouselImageView.setImage(img);
        } catch (Exception e) {
            System.out.println("Fehler: " + imagePaths.get(currentIndex));
        }
    }
}