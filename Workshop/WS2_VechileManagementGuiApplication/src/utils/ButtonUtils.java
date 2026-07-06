package utils;

import javafx.scene.control.Button;

public class ButtonUtils {

    // Apply default style to multiple buttons
    public static void setDefaultStyle(Button... buttons) {
        for (Button b : buttons) {
            b.setStyle(
                    "-fx-border-color: #1d1d1f;" + // dark border
                            "-fx-background-color: #1d1d1f;" + // white background
                            "-fx-border-radius: 5;" +
                            "-fx-background-radius: 5;" +
                            "-fx-font-weight: bold;" +
                            "-fx-padding: 8 15 8 15;" +
                            "-fx-text-fill: #ffffff;");
        }
    }

    // Highlight the selected button, reset others
    public static void highlightButton(Button selected, Button... allButtons) {
        for (Button b : allButtons) {
            if (b == selected) {
                b.setStyle(
                        "-fx-background-color: #0066cc;" + // blue background
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 5;" +
                                "-fx-padding: 8 15 8 15;" +
                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 2, 0, 0, 1);");
            } else {
                setDefaultStyle(b);
            }
        }
    }
}
