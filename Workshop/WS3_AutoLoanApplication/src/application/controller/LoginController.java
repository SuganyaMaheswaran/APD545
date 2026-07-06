package application.controller;

import application.AppInitializer;
import application.repository.UserRepository;
import application.util.AppAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField loginUsernameField;
    @FXML private PasswordField loginPasswordField;
    @FXML private Button loginCancelButton;
    @FXML private Button loginSubmitButton;

    private AppInitializer app;
    private UserRepository userRepository;

    /**
     * Injects the required central controller context dependencies
     */
    public void setDependencies(AppInitializer app, UserRepository repo) {
        this.app = app;
        this.userRepository = repo;
    }

    @FXML
    void handleCancel(ActionEvent event) {
        if (app != null) {
            app.showWelcomeView();
        }
    }

  
   
    @FXML
    void handleSubmit(ActionEvent event) {
        if (app == null || userRepository == null) {
            System.err.println("Dependencies not initialized in LoginController!");
            return;
        }

        String username = loginUsernameField.getText().trim();
        String password = loginPasswordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            AppAlert.showError("Validation Error", "Please enter both username and password.");
            return;
        }
        if (userRepository.validateLogin(username, password)) {
            clearFields();
            // Call the navigation method
            app.showDashboardView(); 
        } else {
        	AppAlert.showError("Login Failed", "Invalid username or password.");
            
            
        }
    }

    private void clearFields() {
        loginUsernameField.clear();
        loginPasswordField.clear();
    }
}