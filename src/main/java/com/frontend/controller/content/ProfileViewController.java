package com.frontend.controller.content;

import com.backend.model.Users;
import com.backend.service.AuthService;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ProfileViewController implements Initializable {
    public Circle profileIcon;
    public Label userNameLabel;
    public Label biographyLabel;
    public GridPane favMediaGridPane;
    public BarChart userGraphChart;
    public ListView overViewListView;
    //public AnchorPane contentPane;
    //public AuthService authService = new AuthService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CategoryAxis categoryAxis = new CategoryAxis();

        categoryAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")));
        categoryAxis.setLabel("Ratings");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total");

        if(AuthService.sessionId != 0){
            try {
                userNameLabel.setText(setUserName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //sets the username
   public String setUserName() throws SQLException {
        return Users.getUser();
   }
}
