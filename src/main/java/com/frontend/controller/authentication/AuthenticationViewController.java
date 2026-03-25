package com.frontend.controller.authentication;

import com.frontend.MainController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;

public class AuthenticationViewController extends MainController {
    public TextField signupUsernameField;
    public PasswordField signupPasswordField;
    public PasswordField signupPasswordRepeatField;

    public TextField usernameField;
    public PasswordField passwordField;

    public Label warningLabel;
    public TextField visiblePasswordField;

    private static final String FILE_PATH = "tempUserdata.csv";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Diese Methode überschreibt die Logik des MainControllers,
     * nutzt aber die geerbte loadContentView Methode.
     */
    @Override
    public void onMenuBarButtonClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof ToggleButton src) {
            String target = src.getId();

            if (target != null && !target.isEmpty()) {
                loadContentView(target);
            }
        }
    }

    public void onLoginClicked(ActionEvent actionEvent) {
        if (usernameField == null || passwordField == null) return;

        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            warningLabel.setText("Error: Please fill all fields");
            return;
        }
        try {
            List<String> lines = Files.readAllLines(Path.of(FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;
                if (username.equals(parts[0]) && password.equals(parts[1])) {
                    warningLabel.setText("Login successful");
                    return;
                }
            }
            warningLabel.setText("Error: Username or Password false");
        } catch (IOException e) {
            warningLabel.setText("Error: ?");
        }
    }

    public void onSignupClicked(ActionEvent actionEvent) {
        if (signupUsernameField == null || signupPasswordField == null || signupPasswordRepeatField == null) return;

        String username = signupUsernameField.getText();
        String password = signupPasswordField.getText();
        String repeatPassword = signupPasswordRepeatField.getText();

        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            warningLabel.setText("Error: Please fill all fields");
            return;
        }
        if (!password.equals(repeatPassword)) {
            warningLabel.setText("Error: Password dont match");
            return;
        }
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                List<String> lines = Files.readAllLines(file.toPath());
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length < 1) continue;
                    if (username.equals(parts[0])) {
                        warningLabel.setText("Error: Username already taken");
                        return;
                    }
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();
            warningLabel.setText("Signup successful");
        } catch (IOException e) {
            warningLabel.setText("Error: Cannot save user");
        }
    }
}