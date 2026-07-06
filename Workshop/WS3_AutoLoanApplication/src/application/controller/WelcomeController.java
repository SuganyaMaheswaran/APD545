package application.controller;

import application.AppInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController {

    @FXML private Button goToLoginBtn;
    @FXML private Button goToSignUpBtn;

    private AppInitializer app;

    // This method is called by AppInitializer immediately after FXML load
    public void setAppInitializer(AppInitializer app) {
        this.app = app;
    }

    @FXML
    public void handleWelcomeButtons(ActionEvent event) {
        if (app != null) {
            if (event.getSource() == goToSignUpBtn) {
                app.showSignupView();
            } else if (event.getSource() == goToLoginBtn) {
                app.showLoginView();
            }
        }
    }
}