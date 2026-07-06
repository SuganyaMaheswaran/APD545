package application.repository;

import application.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepository() {
        // Requirements: At least two hardcoded users must exist by default
        addUser(new User("admin", "admin123", "admin@seneca.ca"));
        addUser(new User("user", "password", "user@seneca.ca"));
    }

    public void addUser(User user) {
        users.put(user.getUsername().toLowerCase(), user);
    }

    public User findUser(String username) {
        if (username == null) return null;
        return users.get(username.toLowerCase());
    }

    public boolean validateLogin(String username, String password) {
        User u = findUser(username);
        return u != null && u.getPassword().equals(password);
    }
}