package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    //  constructor
    public User(String username, String password, String email) {
        this.username.set(username);
        this.password.set(password);
        this.email.set(email);
    }

    // Setters
    public void setUsername(String username) { this.username.set(username); }
    public void setPassword(String password) { this.password.set(password); }
    public void setEmail(String email) { this.email.set(email); }
    
    // Getters and Properties
    public String getUsername() { return username.get(); }
    public StringProperty usernameProperty() { return username; }

    public String getPassword() { return password.get(); }
    public StringProperty passwordProperty() { return password; }

    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }
}