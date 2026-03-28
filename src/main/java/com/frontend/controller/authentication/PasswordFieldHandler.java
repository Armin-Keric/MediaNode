package com.frontend.controller.authentication;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PasswordFieldHandler {

    private PasswordFieldHandler() {

    }

    public static void toggleVisibility(PasswordField passwordField, TextField visibleField) {

        boolean currentlyHidden = passwordField.isVisible();

        passwordField.setVisible(!currentlyHidden);
        passwordField.setManaged(!currentlyHidden);

        visibleField.setVisible(currentlyHidden);
        visibleField.setManaged(currentlyHidden);

        if (currentlyHidden) {
            visibleField.requestFocus();
        } else {
            passwordField.requestFocus();
        }
    }
}
