package com.frontend.controller.authentication;

import javafx.event.ActionEvent;

import java.io.IOException;

public class WelcomeController {

    public void onSignInClicked(ActionEvent actionEvent) throws IOException {
        NavigationHandler.switchTo(actionEvent, "/com/frontend/signin-view.fxml");
    }

    public void onSignUpClicked(ActionEvent actionEvent) throws IOException {
        NavigationHandler.switchTo(actionEvent, "/com/frontend/signup-view.fxml");    }
}
