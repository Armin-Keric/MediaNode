package com.frontend;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PasswordFieldHandler {

    private PasswordFieldHandler() {

    }

    public static void toggleVisibility(PasswordField passwordField, TextField visibleField) {

        boolean currentlyHidden = passwordField.isVisible();

        if (currentlyHidden) {
            visibleField.setText(passwordField.getText());
        } else {
            passwordField.setText(visibleField.getText());
        }

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
