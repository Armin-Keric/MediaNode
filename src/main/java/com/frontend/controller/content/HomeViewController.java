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

public class HomeViewController implements Initializable {

    public ImageView mainCarouselImageView;
    public HBox paginationContainer;

    private final List<String> imageURLs = new ArrayList<>();
    private final ToggleGroup dotGroup = new ToggleGroup();
    private int currentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Test-URL
        imageURLs.add("https://p3-ofp.static.pub//fes/cms/2024/09/12/0htn589or1vvi4zfkiwlxw8zorx5tm707792.png");
        imageURLs.add("https://w0.peakpx.com/wallpaper/178/348/HD-wallpaper-game-of-thrones02-cool-tv-series-entertainment-fun-game-of-thrones-thumbnail.jpg");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqrd0xUAAXtm-P_-79m65AW_SvTPa7ctXPAw&s");
        imageURLs.add("https://images.mubicdn.net/images/film/395525/cache-922134-1745502042/image-w1280.jpg?size=800x");

        setupPagination();
        updateUI(false);
    }

    private void setupPagination() {
        paginationContainer.getChildren().clear();
        for (int i = 0; i < imageURLs.size(); i++) {
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
        if (imageURLs.isEmpty()) return;
        currentIndex = (currentIndex + 1) % imageURLs.size();
        updateUI(true);
    }

    public void onPreviousClicked() {
        if (imageURLs.isEmpty()) return;
        currentIndex = (currentIndex - 1 + imageURLs.size()) % imageURLs.size();
        updateUI(true);
    }

    private void updateUI(boolean useFade) {
        if (imageURLs.isEmpty()) return;

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
            Image img = new Image(imageURLs.get(currentIndex), true);
            mainCarouselImageView.setImage(img);
        } catch (Exception e) {
            System.out.println("Fehler: " + imageURLs.get(currentIndex));
        }
    }
}