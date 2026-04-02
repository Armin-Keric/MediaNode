package com.frontend.controller.content;

import com.backend.service.AuthService;
import com.frontend.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthenticationViewController extends MainController implements Initializable {
    public TextField usernameField;
    public PasswordField passwordField;
    public VBox loginContainer;
    public ToggleButton loginToggle;
    public TextField signupUsernameField;
    public PasswordField signupPasswordField;
    public PasswordField signupPasswordRepeatField;
    public VBox signupContainer;
    public ToggleButton signupToggle;
    public Label warningLabel;

    public AuthService authService = new AuthService();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup internalGroup = new ToggleGroup();
        loginToggle.setToggleGroup(internalGroup);
        signupToggle.setToggleGroup(internalGroup);
    }

    /**
     * Switches between the two "Content Containers"
     *
     * @param event
     */
    public void toggleAuthMode(ActionEvent event) {
        boolean isLogin = event.getSource() == loginToggle;

        loginContainer.setVisible(isLogin);
        loginContainer.setManaged(isLogin);

        signupContainer.setVisible(!isLogin);
        signupContainer.setManaged(!isLogin);

        // clear warningLabel
        warningLabel.setText("");
    }

    public void onLoginClicked(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            warningLabel.setText("Please fill all fields");
            return;
        }

        try {
            // load ProfileLayoutView if everything is ok
            if (authService.checkLogin(username, password)) {
                MainController tmp = MainController.getInstance();
                tmp.loadView(tmp.contentPane, "profile-layout-view.fxml", "AuthenticationController loading ProfileLayout");
                return;
            }

            warningLabel.setText("Please try again");
        } catch (Exception e) {
            warningLabel.setText("Login failed: " + e.getMessage());
        }
    }

    public void onSignupClicked(ActionEvent actionEvent) {
        String username = signupUsernameField.getText();
        String password = signupPasswordField.getText();
        String repeat = signupPasswordRepeatField.getText();

        if (username.isEmpty() || password.isEmpty() || repeat.isEmpty()) {
            warningLabel.setText("Please fill all fields");
            return;
        }

        if (!password.equals(repeat)) {
            warningLabel.setText("Passwords don't match");
            return;
        }

        try {
            authService.saveUser(username, password);
            warningLabel.setText("User created!");
            loginToggle.fire();
        } catch (Exception e) {
            warningLabel.setText("Signup failed: " + e.getMessage());
        }
    }
}
