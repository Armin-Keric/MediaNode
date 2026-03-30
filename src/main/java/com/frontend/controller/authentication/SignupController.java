package com.frontend.controller.authentication;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField repeatPasswordField;
    public Label warningLabel;
    public TextField passwordTextField;
    public TextField repeatPasswordTextField;

    public void onConfirmSignUpClicked(ActionEvent actionEvent) {


        if (passwordField.getText().isEmpty() || usernameField.getText().isEmpty()) {
            warningLabel.setText("Please fill in all Fields!");
            return;
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Text muss nicht mehr in PasswordFieldHanlder herum kopiert werden
        passwordField.textProperty().bindBidirectional(passwordTextField.textProperty());

        repeatPasswordField.textProperty().bindBidirectional(repeatPasswordTextField.textProperty());
    }
}
