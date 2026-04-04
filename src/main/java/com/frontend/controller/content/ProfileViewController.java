package com.frontend.controller.content;

import com.backend.model.Users;
import com.backend.service.AuthService;
import com.backend.service.ListService;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileViewController implements Initializable {
    public Label userNameLabel;
    public BarChart userGraphChart;
    public ListView<String> overViewListView;
    //public AnchorPane contentPane;
    //public AuthService authService = new AuthService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProfileLayoutViewController.getInstance().selectMenuButton("profile-view.fxml");
        try {
            loadBarChart();
            overViewListView.getItems().clear();
            if (AuthService.sessionId != 0) {
                try {
                    userNameLabel.setText(setUserName());
                    overViewListView.getItems().setAll(ListService.getActivityLog(AuthService.sessionId));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //sets the username
    public String setUserName() throws SQLException {
        return Users.getUser();
    }

    public void loadBarChart() throws SQLException {
        NumberAxis yAxis = (NumberAxis) userGraphChart.getYAxis();
        //yAxis.setTickUnit(1); seems like this doesn't work?
        //yAxis.setMinorTickVisible(false);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        userGraphChart.getData().clear();
        userGraphChart.getData().add(ListService.getRatings());
    }
}
