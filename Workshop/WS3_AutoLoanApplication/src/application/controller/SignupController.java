package application.controller;

import application.AppInitializer;
import application.model.User;
import application.repository.UserRepository;
import application.util.AppAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {

    @FXML private TextField signUpUsernameField;
    @FXML private PasswordField signUpPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField emailField;
    @FXML private Button signUpCancelButton;
    @FXML private Button signUpSubmitButton;

    private AppInitializer app;
    private UserRepository userRepository;

    public void setDependencies(AppInitializer app, UserRepository repo) {
        this.app = app;
        this.userRepository = repo;
    }

    @FXML
    void handleCancel(ActionEvent event) {
        if (app != null) {
            // Corrected: Pointing to the correct navigation method
            app.showWelcomeView();
        }
    }

    @FXML
    void handleSubmit(ActionEvent event) {
        if (app == null || userRepository == null) {
            System.err.println("Dependencies are missing in SignupController!");
            return;
        }

        if (validateSignup()) {
            saveUser();
            app.showLoginView(); // Redirect on success
        }
    }

    private boolean validateSignup() {
        String username = signUpUsernameField.getText().trim();
        String password = signUpPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailField.getText().trim();

        // 1. Check for empty fields
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            AppAlert.showError("Missing Information", "All fields are required!");
            return false;
        }

        // 2. Validate Username
        if (!username.matches("^[a-zA-Z0-9_]{4,}$")) {
            AppAlert.showError("Invalid Username", "Username must be at least 4 characters long and can only contain letters, numbers, and underscores (_).");
            return false;
        }

        // 3. Validate Email
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            AppAlert.showError("Validation Error", "Please enter a valid email address (e.g., user@domain.com).");
            return false;
        }

        // 4. Validate Password
        if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$")) {
            AppAlert.showError("Weak Password", "Password must be at least 8 characters long, and contain at least one uppercase letter, one number, and one special character.");
            return false;
        }

        // 5. Check Password Match
        if (!password.equals(confirmPassword)) {
            AppAlert.showError("Registration Error", "Passwords do not match!");
            return false;
        }

        // 6. Check if user already exists
        if (userRepository.findUser(username) != null) {
            AppAlert.showError("Duplicate Account", "Username already exists!");
            return false;
        }

        return true;
    }

    private void saveUser() {
        User newUser = new User(
            signUpUsernameField.getText().trim(), 
            signUpPasswordField.getText(), 
            emailField.getText().trim()
        );
        
        userRepository.addUser(newUser);
        
        // Use AppAlert for the success message to match the theme
        AppAlert.showInfo("Registration Successful", "Account created successfully! Please login.");
        
        clearFields();
    }

    private void clearFields() {
        signUpUsernameField.clear();
        signUpPasswordField.clear();
        confirmPasswordField.clear();
        emailField.clear();
    }
}