package com.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SigninController {

    public TextField usernameField;
    public PasswordField passwordField;
    public TextField passwordTextField;
    public Label warningLabel;

    public void onConfirmSignInClicked(ActionEvent event) {

    }

    public void onShowPasswordClicked(ActionEvent event) {
        PasswordFieldHandler.toggleVisibility(passwordField, passwordTextField);
    }

    public void onBackClicked(ActionEvent actionEvent) throws IOException {
        NavigationHandler.switchTo(actionEvent, "/com/frontend/welcome-view.fxml");
    }
}