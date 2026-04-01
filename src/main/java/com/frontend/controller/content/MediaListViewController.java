package com.frontend.controller.content;

import com.backend.Database;
import com.backend.model.Media;
import com.backend.model.User_library;
import com.backend.service.AuthService;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MediaListViewController extends MainController implements Initializable {
    public ListView<Media> overViewListView;
    public HBox consumingArea;
    public HBox completedArea;
    public HBox planningArea;
    private Database database;
    private Media media;
    private AuthService service = new AuthService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
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
            tmpFriends.setMedia(User_library.consuming.get(i));
        }

        for (int i = 0; i < User_library.completed.size(); ++i) {
            completedArea.getChildren().add(new AnchorPane());
            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) completedArea.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            tmpFriends.setMedia(User_library.completed.get(i));
        }

        for (int i = 0; i < User_library.planning.size(); ++i) {
            planningArea.getChildren().add(new AnchorPane());
            MediaViewController tmpFriends = (MediaViewController) loadView((AnchorPane) planningArea.getChildren().get(i), "media-embed-view.fxml", "BrowseController");
            tmpFriends.setMedia(User_library.planning.get(i));
        }

    }

    //Method of MainController
    protected Object loadView(AnchorPane targetPane, String view, String src) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource(getContentViewFolder() + view));
            Node tmp = contentLoader.load();

            AnchorPane.setTopAnchor(tmp, 0.0);
            AnchorPane.setLeftAnchor(tmp, 0.0);
            AnchorPane.setRightAnchor(tmp, 0.0);
            AnchorPane.setBottomAnchor(tmp, 0.0);

            // should prevent flickering over .clear();, .add();
            targetPane.getChildren().setAll(Collections.singleton(tmp));
            // return controller if still needed
            return contentLoader.getController();
        } catch (IOException e) {
            System.out.printf("[%s] Could not find target fxml", src);
        }

        return null;
    }
    //-
    //Method of MainController
    private String getContentViewFolder() {
        return "/com/frontend/view/content/";
    }
}
