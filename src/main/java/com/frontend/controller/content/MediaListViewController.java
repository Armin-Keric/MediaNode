package com.frontend.controller.content;

import com.backend.Database;
import com.backend.model.Media;
import com.backend.model.User_library;
import com.frontend.MainController;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class MediaListViewController extends MainController implements Initializable {
    public ListView<Media> overViewListView;
    public HBox consumingArea;
    public HBox completedArea;
    public HBox planningArea;
    private Database database;
    private Media media;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            User_library.getUserList();
            initializingLists();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void initializingLists() throws SQLException {
        consumingArea.getChildren().clear();
        completedArea.getChildren().clear();
        planningArea.getChildren().clear();

        List<Media> currentData = null;

        for (int i = 0; i < User_library.consuming.size(); ++i) {
            consumingArea.getChildren().add(new AnchorPane());

            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) consumingArea.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            tmpFriends.setMedia(User_library.consuming.get(i).getImg_url(), User_library.consuming.get(i).getTitle());
        }

        for (int i = 0; i < User_library.completed.size(); ++i) {
            completedArea.getChildren().add(new AnchorPane());
            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) completedArea.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            tmpFriends.setMedia(User_library.completed.get(i).getImg_url(), User_library.completed.get(i).getTitle());
        }

        for (int i = 0; i < User_library.planning.size(); ++i) {
            planningArea.getChildren().add(new AnchorPane());
            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) planningArea.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            tmpFriends.setMedia(User_library.planning.get(i).getImg_url(), User_library.planning.get(i).getTitle());
        }

    }
}