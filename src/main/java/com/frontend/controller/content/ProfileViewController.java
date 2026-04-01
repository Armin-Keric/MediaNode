package com.frontend.controller.content;

import com.backend.service.AuthService;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileViewController implements Initializable {
    public Circle profileIcon;
    public Label userNameLabel;
    public Label biographyLabel;
    public GridPane favMediaGridPane;
    public BarChart userGraphChart;
    public ListView overViewListView;
    public AnchorPane contentPane;
    public AuthService authService = new AuthService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
