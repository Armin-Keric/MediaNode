package com.frontend.controller.authentication;

import com.frontend.MainController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class AuthenticationController extends MainController {
    public TextField signupUsernameField;
    public PasswordField signupPasswordField;
    public PasswordField signupPasswordRepeatField;

    public TextField usernameField;
    public PasswordField passwordField;

    public Label warningLabel;
    public TextField visiblePasswordField;

    private static final String FILE_PATH = "tempUserdata.csv";

    /**
     * Login
     */
    public void onLoginClicked(ActionEvent actionEvent) {
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

                String fileUsername = parts[0];
                String filePassword = parts[1];

                if (username.equals(fileUsername) && password.equals(filePassword)) {
                    warningLabel.setText("Login succesful");
                    return;
                }
            }

            warningLabel.setText("Error: Usernaem or Password false");

        } catch (IOException e) {
            warningLabel.setText("Error: ?");
        }
    }

    /**
     * Signup
     */
    public void onSignupClicked(ActionEvent actionEvent) {
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

            List<String> lines = Files.readAllLines(file.toPath());

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length < 1) continue;

                if (username.equals(parts[0])) {
                    warningLabel.setText("Error: Username already taken");
                    return;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();

            warningLabel.setText("Signup succesful");

        } catch (IOException e) {
            warningLabel.setText("Error: Cannot save user");
        }
    }
}