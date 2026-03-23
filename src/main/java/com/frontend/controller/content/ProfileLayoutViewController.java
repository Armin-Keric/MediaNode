package com.frontend.controller.content;

import com.frontend.MainController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileLayoutViewController extends MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadContentView("profile-view.fxml");
    }
}
