package com.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    public void onSignInClicked(ActionEvent actionEvent) throws IOException {
        NavigationHandler.switchTo(actionEvent, "/com/frontend/signin-view.fxml");
    }

    public void onSignUpClicked(ActionEvent actionEvent) throws IOException {
        NavigationHandler.switchTo(actionEvent, "/com/frontend/signup-view.fxml");    }
}
