package com.frontend.controller.content;

import com.backend.model.User_library;
import com.backend.service.AuthService;
import com.frontend.MainController;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileLayoutViewController extends MainController implements Initializable {
    private static ProfileLayoutViewController instance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;

        //here we need to look if it really IS a ToggleButton
        for (Node node : menuBarHBox.getChildren()) {
            if (node instanceof ToggleButton && node.getId() != null) {
                if (!node.getId().equals("groupIgnore") && !node.getId().isEmpty()) {
                    ToggleButton tmp = (ToggleButton) node;
                    tmp.setToggleGroup(menuBarToggleGroup);
                }
            }
        }
        loadContentView("profile-view.fxml");

        if (AuthService.sessionId != 0) {
            try {
                User_library.getUserList(AuthService.sessionId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return;
        }

        System.out.println("Falsch!");
    }

    //same thing like in the MainController
    public static ProfileLayoutViewController getInstance() {
        return instance;
    }
}

