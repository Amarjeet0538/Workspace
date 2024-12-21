package com.thebank.thebank.Controllers;

import com.thebank.thebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public Button signin_btn;
    public Button signup_btn;
    public Label error_lbl;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password; // Changed to PasswordField for better security
    @FXML
    private CheckBox showPassword; // Checkbox to toggle password visibility

    private Stage mainWindow;
    private boolean isPasswordVisible = false;

    public void setMainWindow(Stage stage) {
        this.mainWindow = stage;
        mainWindow.setTitle("The Bank");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signin_btn.setOnAction(event-> onLogin());
        signup_btn.setOnAction(event -> onClick_Signup(event));
    }

    @FXML
    void onClick_Signin(ActionEvent event) {
        // Load and display the dashboard scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mazebank/mazebank/views/Dashboard.fxml"));
            Parent dashboardRoot = loader.load();
            Scene dashboardScene = new Scene(dashboardRoot);
            mainWindow.setScene(dashboardScene);
            mainWindow.setTitle("Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to open Dashboard.");
        }
    }

    @FXML
    void onClick_Signup(ActionEvent event) {
        try {
            System.out.println(getClass().getResource("/main/resources/Fxml/signup.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/Fxml/signup.fxml"));
            Parent signupRoot = loader.load();
            Scene signupScene = new Scene(signupRoot);
            mainWindow.setScene(signupScene);
            mainWindow.setTitle("Sign Up");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to open Signup.");
        }
    }


    @FXML
    void onClick_Clear(ActionEvent event) {
        username.clear();
        password.clear();
    }


    @FXML

    void onClick_ShowPassword(ActionEvent event) {
        if (isPasswordVisible) {
            password.setText(password.getPromptText());
            password.setPromptText("");
            showPassword.setText("Show Password");
        } else {
            password.setPromptText(password.getText());
            password.setText("");
            showPassword.setText("Hide Password");
        }
        isPasswordVisible = !isPasswordVisible;

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onLogin() {
        Stage stage = (Stage) username.getScene().getWindow();
        Model.getInstance().evaluateClientCred(username.getText(), password.getText());

        if (Model.getInstance().getClientLoginSucessFlag()) {
            String loggedInUsername = username.getText();
            Model.getInstance().getViewFactory().showDashboardWindow(loggedInUsername);
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            username.clear();
            password.clear();
            error_lbl.setText("No such Login Credentials");
        }
    }

}
