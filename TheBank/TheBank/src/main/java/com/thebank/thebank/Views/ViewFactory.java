package com.thebank.thebank.Views;

import com.thebank.thebank.Controllers.dashboardcontroller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {
    private AnchorPane dashboardView;

    public ViewFactory() {}

    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login_interface.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("The Bank");
        stage.show();
    }

    public void showDashboardWindow(String loggedInUsername) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml"));
        Scene scene = null;
        try {
            // Load the FXML
            scene = new Scene(loader.load());

            // Get the controller and set the username
            dashboardcontroller controller = loader.getController();
            controller.setLoggedInUsername(loggedInUsername); // Pass the logged-in username

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show the new stage
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }




    public void closeStage(Stage stage) {
        stage.close();
    }

}
