package com.frontend.controller.content;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MediaViewController {
    public ComboBox<String> addToListComboBox;
    public Label ratingLabel;
    public Label titleLabel;
    public ImageView imageImageView;

    public void onAddToListComboBoxClicked(ActionEvent actionEvent) {

    }

    /**
     * sets the image, title and rating for an already loaded fxml
     * meant for Media-Embed-View.fxml
     *
     * @param imageUrl String
     * @param title String
     * @param rating String (n/n)
     */
    public void setMedia(String imageUrl, String title, String rating) {
        titleLabel.setText(title);
        ratingLabel.setText(rating);

        // set image
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // needed for some servers
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int serverResponse = connection.getResponseCode();

            if (serverResponse == HttpURLConnection.HTTP_OK) {
                try (InputStream is = connection.getInputStream()) {
                    Image image = new Image(is);
                    imageImageView.setImage(image);
                }
            } else {
                System.out.println("[MediaViewController] Response Code: " + serverResponse);
                loadLocalImage("Placeholder-Media-Embed.jpg");
            }
        } catch (Exception e) {
            loadLocalImage("Placeholder-Media-Embed.jpg");
        }
    }

    /**
     * If something happens while trying to load the (url) image, load one locally stored
     *
     * @param img image in /com/frontend/
     */
    private void loadLocalImage(String img) {
        InputStream placeholderStream = getClass().getResourceAsStream("/com/frontend/" + img);

        if (placeholderStream != null) {
            System.out.println("[MediaViewController] Loading local image...");
            imageImageView.setImage(new Image(placeholderStream));
        }
    }
}
