package application.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AppAlert {

    /**
     * Shows a styled Error alert
     */
    public static void showError(String title, String message) {
        showAlert(AlertType.ERROR, title, message);
    }

    /**
     * Shows a styled Information alert
     */
    public static void showInfo(String title, String message) {
        showAlert(AlertType.INFORMATION, title, message);
    }

    /**
     * Internal helper to apply theme and show the alert
     */
    private static void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null); 
        alert.setContentText(message);
        
        
        try {
            String css = AppAlert.class.getResource("/application/application.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
        } catch (Exception e) {
            System.err.println("Could not load CSS for Alert");
        }
        
        alert.showAndWait();
    }
}