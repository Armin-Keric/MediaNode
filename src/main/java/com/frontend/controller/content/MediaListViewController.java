package com.frontend.controller.content;

import com.backend.Database;
import com.backend.model.Media;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MediaListViewController implements Initializable {
    public ListView<Media> overViewListView;
    private Database database;
    private Media media;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            database = Database.getInstance();
            Connection c = database.getConnection();
            Statement statement = c.createStatement();
            //overViewListView.getItems().addAll(Media.medias("ASC"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
