package com.frontend;

import com.backend.model.User_library;
import com.backend.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public HBox menuBarHBox;
    public AnchorPane contentPane;
    private static MainController instance;
    protected final ToggleGroup menuBarToggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;

        for (Node node : menuBarHBox.getChildren()) {
            if (!node.getId().equals("groupIgnore") && !node.getId().isEmpty()) {
                ToggleButton tmp = (ToggleButton) node;
                tmp.setToggleGroup(menuBarToggleGroup);
            }
        }
        selectMenuButton("home-view.fxml");

        // load a default site
        loadContentView("home-view.fxml");
    }

    /**
     * gets the name of the fxml file that should be loaded to `contentPane` from the Button ID
     * and calls loadContentView with that value
     *
     * @param actionEvent clicked Button
     */
    public void onMenuBarButtonClicked(ActionEvent actionEvent) throws SQLException {
        ToggleButton src = (ToggleButton) actionEvent.getSource();
        String target = src.getId();

        if (!target.isEmpty()) {
            loadContentView(target);
        }
    }

    /**
     * toggles the correct Button in the navbar
     * (used in ProfileLayoutViewController)
     *
     * @param buttonId target ToggleButton
     */
    public void selectMenuButton(String buttonId) {
        for (Node node : menuBarHBox.getChildren()) {
            if (node instanceof ToggleButton && buttonId.equals(node.getId())) {
                ((ToggleButton) node).setSelected(true);
                break;
            }
        }
    }

    /**
     * loads a fxml file in `contentPane`
     *
     * @param view fxml file in /com/frontend/view/content/
     */
    public void loadContentView(String view) {
        loadView(contentPane, view, "MainController");
    }

    /**
     * loads a fxml file to a pane
     *
     * @param targetPane pane where the fxml file should be loaded
     * @param view       fxml file in /com/frontend/view/content/
     * @param src        name of the controller for debugging
     * @return the controller or null
     */
    public Object loadView(AnchorPane targetPane, String view, String src) {
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

    private String getContentViewFolder() {
        return "/com/frontend/view/content/";
    }

    /**
     * if a loaded pane wants tho load something directly on the main-view
     *
     * @return this
     */
    public static MainController getInstance() {
        return instance;
    }
}
