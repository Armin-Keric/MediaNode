package com.frontend;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

public class SignupController {

    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField repeatPasswordField;
    public Label warningLabel;
    public TextField passwordTextField;
    public TextField repeatPasswordTextField;

    public void onConfirmSignUpClicked(ActionEvent actionEvent) {
    }

    public void onShowPasswordClicked(ActionEvent actionEvent) {
        PasswordFieldHandler.toggleVisibility(passwordField, passwordTextField);
    }

    public void onShowRepeatPasswordClicked(ActionEvent actionEvent) {
        PasswordFieldHandler.toggleVisibility(repeatPasswordField, repeatPasswordTextField);
    }

    public void onBackClicked(ActionEvent actionEvent)  throws IOException {
        NavigationHandler.switchTo(actionEvent, "/com/frontend/welcome-view.fxml");
    }
}
