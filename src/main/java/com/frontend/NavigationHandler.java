package com.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationHandler {

    private NavigationHandler() {

    }

    public static void switchTo(ActionEvent actionEvent, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(NavigationHandler.class.getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
