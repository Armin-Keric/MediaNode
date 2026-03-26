package com.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public HBox menuBarHBox;
    public AnchorPane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // load a default site
        loadContentView("authentication-view.fxml");

    }


    /**
     * gets the name of the fxml file that should be loaded to `contentPane` from the Button ID
     * and calls loadContentView with that value
     *
     * @param actionEvent clicked Button
     */
    public void onMenuBarButtonClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        String target = src.getId();

        if (!target.isEmpty()) {
            System.out.println("Trying to load: " + getClass().getResource(getContentViewFolder() + target));
            loadContentView(target);
        }
    }

    /**
     * loads a fxml file in `contentPane`
     *
     * @param view fxml file in resources/com/frontend/'CONTENT_VIEW_FOLDER'
     */
    protected void loadContentView(String view) {
        loadView(contentPane, view, "MainController");
    }

    protected void loadView(AnchorPane targetPane, String view, String src) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource(getContentViewFolder() + view));
            Node tmp = contentLoader.load();

            AnchorPane.setTopAnchor(tmp, 0.0);
            AnchorPane.setLeftAnchor(tmp, 0.0);
            AnchorPane.setRightAnchor(tmp, 0.0);
            AnchorPane.setBottomAnchor(tmp, 0.0);

            // should prevent flickering over .clear();, .add();
            targetPane.getChildren().setAll(Collections.singleton(tmp));
        } catch (IOException e) {
            System.out.printf("[%s] Could not find target fxml", src);
        }
    }

    private String getContentViewFolder() {
        return "/com/frontend/view/content/";
    }
}
