package com.frontend.controller.content;

import com.backend.model.Media;
import com.frontend.MainController;
import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController extends MainController implements Initializable {

    public ImageView mainCarouselImageView;
    public HBox paginationContainer;

    private final List<String> imageURLs = new ArrayList<>();
    private final List<Media> carouselMedias = new ArrayList<>();
    private final ToggleGroup dotGroup = new ToggleGroup();
    public HBox trendingArea;
    private int currentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carouselMedias.addAll(Media.medias_release("DESC"));
            imageURLs.add("https://pbs.twimg.com/media/HA_klpObUAAqglN?format=jpg&name=large");
            imageURLs.add("https://www.kojimaproductions.jp/sites/default/files/2025-03/ds2_keyimage_withoutreleasedate_16x9.jpg");
            imageURLs.add("https://d1w82usnq70pt2.cloudfront.net/wp-content/uploads/2025/05/clairobscur.jpeg");
            imageURLs.add("https://image.api.playstation.com/vulcan/ap/rnd/202110/2000/YMUoJUYNX0xWk6eTKuZLr5Iw.jpg");

            setupPagination();
            updateUI(false);
            setTrendingArea();
            mainCarouselImageView.setOnMouseClicked(e -> {
                try {
                    Media selectedMedia = carouselMedias.get(currentIndex);

                    // Der gleiche Code wie in deinem MediaViewController
                    MainController main = MainController.getInstance();
                    MediaDetailsViewController controller = (MediaDetailsViewController) main.loadView(
                            main.contentPane,
                            "media-fullscreen-view.fxml",
                            "Carousel Click"
                    );

                    controller.setMedia(selectedMedia);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTrendingArea() throws SQLException {
        int OBJECTS_PER_ROW = 5;
        int x = 0;
        int y = 0;

        for (int i = 0; i < OBJECTS_PER_ROW; ++i) {
            trendingArea.getChildren().add(new AnchorPane());

            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) trendingArea.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            tmpFriends.setMedia(Media.medias_release("DESC").get(i));
        }
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