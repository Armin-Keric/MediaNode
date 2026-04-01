package com.frontend.controller.authentication;

import com.backend.service.AuthService;
import com.frontend.MainController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

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
    public AuthService authService = new AuthService();

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
            //@ToDo
            //Abgleichen mit dem Wert aus der DB. wichtig werden user_id sein usw....
            authService.checkLogin(username,password);

        } catch (Exception e) {
            throw new RuntimeException(e);
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
            //@ToDo
            //Hier senden wir ans backend die Sachen (username, password) die er ins
            //table anfügen soll, sofern der name noch nicht existiert.
            authService.saveUser(username, password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}