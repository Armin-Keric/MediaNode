package com.frontend.controller.content;

import com.backend.model.Media;
import com.frontend.MainController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class MediaViewController {
    public Label titleLabel;
    public ImageView imageImageView;

    private Media media;

    /**
     * sets the image, title and rating for an already loaded fxml
     * meant for Media-Embed-View.fxml
     *
     */
    public void setMedia(Media m) {
        this.media = m;

        try {
            titleLabel.setText(m.getTitle());

            // set image
            URL url = new URL(m.getImg_url());
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

    public void onMediaEmbedClicked(MouseEvent mouseEvent) {
        MainController main = MainController.getInstance();
        MediaDetailsViewController controller = (MediaDetailsViewController) main.loadView(
                main.contentPane,
                "media-fullscreen-view.fxml",
                "[MediaViewController loading MediaDetailsViewController]"
        );

        controller.setMedia(media);
    }
}