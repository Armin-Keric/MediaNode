package com.frontend.controller.content;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseViewController implements Initializable {
    public TextField searchTextField;
    public ComboBox<String> mediaTypeComboBox;
    public ComboBox<String> mediaGenreComboBox;
    public ComboBox<String> mediaYearComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add combobox options
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/frontend/data/browse-filteroptions.csv"))) {
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
